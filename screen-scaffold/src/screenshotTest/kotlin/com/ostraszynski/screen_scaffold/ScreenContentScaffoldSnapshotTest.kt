package com.ostraszynski.screen_scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.android.tools.screenshot.PreviewTest

@PreviewTest
@PreviewLightDark
@Composable
private fun ScreenContentScaffoldNoHeaderNoFooterSnapshot() {
    ScreenContentScaffoldSnapshotContent()
}

@PreviewTest
@PreviewLightDark
@Composable
private fun ScreenContentScaffoldHeaderOnlySnapshot() {
    ScreenContentScaffoldSnapshotContent(header = true)
}

@PreviewTest
@PreviewLightDark
@Composable
private fun ScreenContentScaffoldFooterOnlySnapshot() {
    ScreenContentScaffoldSnapshotContent(footer = true)
}

@PreviewTest
@PreviewLightDark
@Composable
private fun ScreenContentScaffoldHeaderAndFooterSnapshot() {
    ScreenContentScaffoldSnapshotContent(header = true, footer = true)
}

@PreviewTest
@PreviewLightDark
@Composable
private fun ScreenContentScaffoldTransparentFooterSnapshot() {
    ScreenContentScaffoldSnapshotContent(
        header = true,
        footer = true,
        transparentFooter = true,
    )
}

@PreviewTest
@PreviewLightDark
@Composable
private fun ScreenContentScaffoldLazyColumnSnapshot() {
    ScreenContentScaffoldLazySnapshotContent()
}

@PreviewTest
@PreviewLightDark
@Composable
private fun ScreenContentScaffoldTabNavigationNoFooterSnapshot() {
    ScreenContentScaffoldSnapshotContent(
        header = true,
        tabNavigationBarShown = true,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContentScaffoldSnapshotContent(
    header: Boolean = false,
    footer: Boolean = false,
    transparentFooter: Boolean = false,
    tabNavigationBarShown: Boolean = false,
) {
    MaterialTheme {
        ScreenContentScaffold(
            modifier = Modifier.fillMaxSize(),
            header = if (header) {
                { ScreenContentScaffoldSnapshotHeader() }
            } else {
                null
            },
            footer = if (footer) {
                { ScreenContentScaffoldSnapshotFooter(transparent = transparentFooter) }
            } else {
                null
            },
            contentPadding = PaddingValues(16.dp),
            tabNavigationBarShown = tabNavigationBarShown,
            content = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Top content",
                )
                Spacer(modifier = Modifier.height(560.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom content",
                )
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContentScaffoldLazySnapshotContent() {
    MaterialTheme {
        ScreenContentScaffold(
            modifier = Modifier.fillMaxSize(),
            header = { ScreenContentScaffoldSnapshotHeader() },
            footer = { ScreenContentScaffoldSnapshotFooter() },
            contentPadding = PaddingValues(16.dp),
            lazyColumnContent = {
                items((0 until 20).toList()) { index ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(horizontal = 16.dp),
                        text = "Lazy item $index",
                    )
                }
            },
        )
    }
}

@Composable
private fun ScreenContentScaffoldSnapshotHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Header")
    }
}

@Composable
private fun ScreenContentScaffoldSnapshotFooter(transparent: Boolean = false) {
    val backgroundColor = if (transparent) Color.Transparent else MaterialTheme.colorScheme.secondaryContainer

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(backgroundColor)
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Footer")
    }
}
