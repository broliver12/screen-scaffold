package com.ostraszynski.screen_scaffold

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
private fun ScreenContentScaffoldPreview() {
    ScreenContentScaffold(
        modifier = Modifier
            .fillMaxSize()
            .border(1.dp, Red),
        content = {
            Text("Hello Screen Wrapper")
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
private fun ScreenContentScaffoldHeaderFooterContentPaddingPreview() {
    ScreenContentScaffold(
        modifier = Modifier
            .fillMaxSize()
            .border(1.dp, Red),
        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 16.dp,
        ),
        scrollState = rememberScrollState(initial = Int.MAX_VALUE),
        header = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Green)
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                text = "Header",
            )
        },
        footer = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Blue)
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                text = "Footer",
            )
        },
        content = {
            Text(
                modifier = Modifier.padding(top = 1000.dp),
                text = "Hello Screen Wrapper",
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
private fun ScreenContentScaffoldHeaderFooterPreview() {
    ScreenContentScaffold(
        modifier = Modifier
            .fillMaxSize()
            .border(1.dp, Red),
        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 16.dp,
        ),
        header = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Green)
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                text = "Header",
            )
        },
        footer = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Blue)
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                text = "Footer",
            )
        },
        content = {
            Text("Hello Screen Wrapper")
        },
    )
}
