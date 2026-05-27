package com.ostraszynski.screen_scaffold_example.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ostraszynski.screen_scaffold.ScreenContentScaffold
import com.ostraszynski.screen_scaffold_example.ui.components.ExampleFooter
import com.ostraszynski.screen_scaffold_example.ui.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputFormWithFooterScreen(
    backPressed: () -> Unit,
    showTopAppBar: Boolean,
    showBottomNav: Boolean,
    showFooter: Boolean,
) {
    var inputText by remember {
        mutableStateOf("")
    }

    ScreenContentScaffold(
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
        footer = if (showFooter) {
            {
                ExampleFooter()
            }
        } else {
            null
        },
        header = if (showTopAppBar) {
            {
                TopBar(
                    title = "Input Form in Column",
                    showBackNavigation = true,
                    onBackClicked = backPressed
                )
            }
        } else {
            null
        },
        tabNavigationBarShown = showBottomNav,
    )
}
