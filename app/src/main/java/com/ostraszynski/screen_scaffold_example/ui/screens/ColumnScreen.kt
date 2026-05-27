package com.ostraszynski.screen_scaffold_example.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.ostraszynski.screen_scaffold.ScreenContentScaffold
import com.ostraszynski.screen_scaffold_example.ui.components.ExampleFooter
import com.ostraszynski.screen_scaffold_example.ui.components.ListItem
import com.ostraszynski.screen_scaffold_example.ui.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScreen(
    backPressed: () -> Unit,
    showTopAppBar: Boolean,
    showBottomNav: Boolean,
    showFooter: Boolean,
) {
    val items = List(100) { i -> i}
    ScreenContentScaffold(
        header = if (showTopAppBar) {
            {
                TopBar(
                    title = "Scrollable Column",
                    showBackNavigation = true,
                    onBackClicked = backPressed
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
        content = {
            items.forEach {
                ListItem(value = it)
            }
        },
    )
}
