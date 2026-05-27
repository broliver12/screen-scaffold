package com.ostraszynski.screen_scaffold_example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.ostraszynski.screen_scaffold.ScreenContentScaffold
import com.ostraszynski.screen_scaffold_example.ui.components.ExampleFooter
import com.ostraszynski.screen_scaffold_example.ui.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen(
    goToColumnScreen: () -> Unit,
    goToLazyColumnScreen: () -> Unit,
    goToInputFormScreen: () -> Unit,
    showTopAppBar: Boolean,
    showBottomNav: Boolean,
    showFooter: Boolean,
) {
    ScreenContentScaffold(
        header = if (showTopAppBar) {
            {
                TopBar(
                    title = "Screen Scaffold Demo App"
                )
            }
        } else {
            null
        },
        footer = if (showFooter) {
            {
                ExampleFooter()
            }
        } else {
            null
        },
        tabNavigationBarShown = showBottomNav,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Button(
                onClick = goToColumnScreen
            ) {
                Text("Column Screen")
            }

            Button(
                onClick = goToLazyColumnScreen
            ) {
                Text("Lazy Column Screen")
            }

            Button(
                onClick = goToInputFormScreen
            ) {
                Text("Input Form Screen")
            }
        },
    )
}
