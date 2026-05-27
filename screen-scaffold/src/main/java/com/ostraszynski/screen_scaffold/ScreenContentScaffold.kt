package com.ostraszynski.screen_scaffold

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp

/**
 * Generic composable screen scaffold for regular scrollable column content.
 *
 * @param header App bar/header slot. Assumed to handle status bar padding if provided.
 * @param footer Sticky footer slot. Assumed to handle navigation bar padding if provided.
 * @param scrollState Optional scroll state for the main column content.
 * @param scrollBehavior Optional top app bar scroll behavior for connected app bars.
 * @param contentPadding Internal content padding for the main column.
 * @param tabNavigationBarShown Set to true when using a bottom tab bar with no footer.
 * @param applyImePadding Set to true if IME padding isn't applied at scaffold level.
 * @param verticalArrangement Vertical arrangement for the main column when content is smaller than the available body.
 * @param horizontalAlignment Horizontal alignment for the main column content.
 * @param content Main scrollable column content.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContentScaffold(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    footer: (@Composable () -> Unit)? = null,
    scrollState: ScrollState = rememberScrollState(),
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        canScroll = { false },
        state = rememberTopAppBarState(),
    ),
    contentPadding: PaddingValues = PaddingValues(),
    tabNavigationBarShown: Boolean = false,
    applyImePadding: Boolean = false,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit,
) {
    ScreenContentScaffoldLayout(
        modifier = modifier,
        header = header,
        footer = footer,
        scrollBehavior = scrollBehavior,
        contentPadding = contentPadding,
        applyImePadding = applyImePadding,
        tabNavigationBarShown = tabNavigationBarShown,
    ) { bodyModifier, appliedContentPadding ->
        BoxWithConstraints(
            modifier = bodyModifier,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = maxHeight)
                    .verticalScroll(scrollState)
                    .padding(paddingValues = appliedContentPadding),
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAlignment,
            ) {
                content()
            }
        }
    }
}

/**
 * Generic composable screen scaffold for lazy list content.
 *
 * @param header App bar/header slot. Assumed to handle status bar padding if provided.
 * @param footer Sticky footer slot. Assumed to handle navigation bar padding if provided.
 * @param listState Optional list state for the lazy column content.
 * @param scrollBehavior Optional top app bar scroll behavior for connected app bars.
 * @param contentPadding Internal content padding for the lazy column.
 * @param tabNavigationBarShown Set to true when using a bottom tab bar with no footer.
 * @param applyImePadding Set to true if IME padding isn't applied at scaffold level.
 * @param lazyColumnContent Main scrollable lazy column content.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContentScaffold(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    footer: (@Composable () -> Unit)? = null,
    listState: LazyListState = rememberLazyListState(),
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        canScroll = { false },
        state = rememberTopAppBarState(),
    ),
    contentPadding: PaddingValues = PaddingValues(),
    tabNavigationBarShown: Boolean = false,
    applyImePadding: Boolean = false,
    lazyColumnContent: LazyListScope.() -> Unit,
) {
    ScreenContentScaffoldLayout(
        modifier = modifier,
        header = header,
        footer = footer,
        scrollBehavior = scrollBehavior,
        contentPadding = contentPadding,
        tabNavigationBarShown = tabNavigationBarShown,
        applyImePadding = applyImePadding,
    ) { bodyModifier, appliedContentPadding ->
        LazyColumn(
            modifier = bodyModifier,
            state = listState,
            contentPadding = appliedContentPadding,
        ) {
            lazyColumnContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContentScaffoldLayout(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)?,
    footer: (@Composable () -> Unit)?,
    scrollBehavior: TopAppBarScrollBehavior,
    contentPadding: PaddingValues,
    tabNavigationBarShown: Boolean,
    applyImePadding: Boolean,
    body: @Composable (
        bodyModifier: Modifier,
        appliedContentPadding: PaddingValues,
    ) -> Unit,
) {
    var footerHeight by remember {
        mutableStateOf(0.dp)
    }
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val systemBars = WindowInsets.systemBars.asPaddingValues()
    val keyboardOpen by keyboardAsState()

    val noHeaderTopPadding = if (header == null) {
        systemBars.calculateTopPadding()
    } else {
        0.dp
    }

    val noFooterBottomPadding = systemBars.calculateBottomPadding()

    val appliedBottomPadding = remember(
        footer,
        footerHeight,
        noFooterBottomPadding,
        tabNavigationBarShown,
        keyboardOpen,
    ) {
        if (footer == null && !tabNavigationBarShown && !keyboardOpen) {
            noFooterBottomPadding
        } else {
            footerHeight
        }
    }

    val appliedContentPadding = remember(
        contentPadding,
        appliedBottomPadding,
        noHeaderTopPadding,
        header,
        layoutDirection,
    ) {
        PaddingValues(
            bottom = contentPadding.calculateBottomPadding() + appliedBottomPadding,
            top = contentPadding.calculateTopPadding() + noHeaderTopPadding,
            start = contentPadding.calculateStartPadding(layoutDirection),
            end = contentPadding.calculateEndPadding(layoutDirection),
        )
    }

    Box(
        modifier = modifier
            .then(
                if (applyImePadding) {
                    Modifier.imePadding()
                } else {
                    Modifier
                }
            )
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            header?.let {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    it()
                }
            }

            body(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                appliedContentPadding,
            )
        }

        footer?.let {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { layoutCoordinates ->
                        with(density) {
                            footerHeight = layoutCoordinates.size.height.toDp()
                        }
                    }
                    .align(Alignment.BottomCenter),
            ) {
                it()
            }
        } ?: run {
            LaunchedEffect(Unit) {
                footerHeight = 0.dp
            }
        }
    }
}

private const val IME_HEIGHT_DP = 128

@Composable
internal fun keyboardAsState(): State<Boolean> {
    val ime = WindowInsets.ime
    val density = LocalDensity.current

    return remember {
        derivedStateOf {
            ime.getBottom(density) > IME_HEIGHT_DP
        }
    }
}
