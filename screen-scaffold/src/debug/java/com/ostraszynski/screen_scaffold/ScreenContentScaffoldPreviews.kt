package com.ostraszynski.screen_scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp

@PreviewLightDark
@Composable
private fun ScreenContentScaffoldRootPreview() {
    ScreenContentScaffoldExampleTheme {
        ExampleRootScreen()
    }
}

@PreviewLightDark
@Composable
private fun ScreenContentScaffoldColumnPreview() {
    ScreenContentScaffoldExampleTheme {
        ExampleColumnScreen()
    }
}

@PreviewLightDark
@Composable
private fun ScreenContentScaffoldColumnScrolledPreview() {
    ScreenContentScaffoldExampleTheme {
        ExampleColumnScreen(scrolled = true)
    }
}

@PreviewLightDark
@Composable
private fun ScreenContentScaffoldLazyColumnPreview() {
    ScreenContentScaffoldExampleTheme {
        ExampleLazyColumnScreen()
    }
}

@PreviewLightDark
@Composable
private fun ScreenContentScaffoldLazyColumnScrolledPreview() {
    ScreenContentScaffoldExampleTheme {
        ExampleLazyColumnScreen(scrolled = true)
    }
}

@PreviewLightDark
@Composable
private fun ScreenContentScaffoldInputFormPreview() {
    ScreenContentScaffoldExampleTheme {
        ExampleInputFormScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExampleRootScreen() {
    ScreenContentScaffold(
        modifier = Modifier.fillMaxSize(),
        header = {
            ExampleTopBar(title = "Screen Scaffold Demo App")
        },
        footer = {
            ExampleFooter()
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Button(onClick = {}) {
                Text("Column Screen")
            }
            Button(onClick = {}) {
                Text("Lazy Column Screen")
            }
            Button(onClick = {}) {
                Text("Input Form Screen")
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExampleColumnScreen(scrolled: Boolean = false) {
    val scrollBehavior = exampleTopAppBarScrollBehavior(scrolled)

    ScreenContentScaffold(
        modifier = Modifier.fillMaxSize(),
        scrollState = rememberScrollState(initial = if (scrolled) 960 else 0),
        scrollBehavior = scrollBehavior,
        header = {
            ExampleTopBar(
                title = "Scrollable Column",
                showBackNavigation = true,
                scrollBehavior = scrollBehavior,
            )
        },
        footer = {
            ExampleFooter()
        },
        content = {
            repeat(100) { value ->
                ExampleListItem(value = value)
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExampleLazyColumnScreen(scrolled: Boolean = false) {
    val scrollBehavior = exampleTopAppBarScrollBehavior(scrolled)

    ScreenContentScaffold(
        modifier = Modifier.fillMaxSize(),
        listState = rememberLazyListState(initialFirstVisibleItemIndex = if (scrolled) 20 else 0),
        scrollBehavior = scrollBehavior,
        header = {
            ExampleTopBar(
                title = "Scrollable Lazy Column",
                showBackNavigation = true,
                scrollBehavior = scrollBehavior,
            )
        },
        footer = {
            ExampleFooter()
        },
        lazyColumnContent = {
            items(100) { value ->
                ExampleListItem(value = value)
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExampleInputFormScreen() {
    var inputText by remember {
        mutableStateOf("")
    }

    ScreenContentScaffold(
        modifier = Modifier.fillMaxSize(),
        header = {
            ExampleTopBar(
                title = "Input Form in Column",
                showBackNavigation = true,
            )
        },
        footer = {
            ExampleFooter()
        },
        content = {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = inputText,
                onValueChange = {
                    inputText = it
                },
                label = {
                    Text("Demo input")
                },
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExampleTopBar(
    title: String,
    showBackNavigation: Boolean = false,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (showBackNavigation) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Navigate back",
                    )
                }
            }
        },
        title = {
            Text(text = title)
        },
    )
}

@Composable
private fun ExampleFooter(transparent: Boolean = false) {
    val backgroundColor = if (transparent) Color.Transparent else Color.LightGray

    Box(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .height(64.dp)
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Button(onClick = {}) {
            Text("Show Toast")
        }
    }
}

@Composable
private fun ExampleListItem(value: Int) {
    val backgroundColor = if (value % 2 == 0) Color.LightGray else Color.DarkGray
    val textColor = if (value % 2 == 0) Color.Black else Color.White

    Box(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .height(48.dp),
    ) {
        Text(
            text = value.toString(),
            color = textColor,
        )
    }
}

@Composable
private fun ScreenContentScaffoldExampleTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(content = content)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun exampleTopAppBarScrollBehavior(scrolled: Boolean): TopAppBarScrollBehavior {
    val state = rememberTopAppBarState(initialContentOffset = if (scrolled) 1f else 0f)

    return TopAppBarDefaults.pinnedScrollBehavior(state = state)
}
