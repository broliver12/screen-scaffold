package com.ostraszynski.screen_scaffold_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ostraszynski.screen_scaffold_example.ui.components.BottomNavBar
import com.ostraszynski.screen_scaffold_example.ui.screens.ColumnScreen
import com.ostraszynski.screen_scaffold_example.ui.screens.InputFormWithFooterScreen
import com.ostraszynski.screen_scaffold_example.ui.screens.LazyColumnScreen
import com.ostraszynski.screen_scaffold_example.ui.screens.RootScreen
import com.ostraszynski.screen_scaffold_example.ui.theme.ScreenScaffoldExampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            var showBottomNav by remember {
                mutableStateOf(false)
            }
            var showTopAppBar by remember {
                mutableStateOf(true)
            }
            var showFooter by remember {
                mutableStateOf(true)
            }
            var actionsMenuExpanded by remember {
                mutableStateOf(false)
            }

            ScreenScaffoldExampleTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentWindowInsets = WindowInsets(0, 0, 0, 0),
                    bottomBar = {
                        if (showBottomNav) {
                            BottomNavBar()
                        }
                    },
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .consumeWindowInsets(innerPadding)
                            .imePadding()
                            .fillMaxSize()
                    ) {
                        NavHost(
                            modifier = Modifier.fillMaxSize(),
                            navController = navController,
                            startDestination = "root",
                        ) {
                            composable(route = "root") {
                                RootScreen(
                                    goToColumnScreen = {
                                        navController.navigate("column")
                                    },
                                    goToLazyColumnScreen = {
                                        navController.navigate("lazycolumn")
                                    },
                                    goToInputFormScreen = {
                                        navController.navigate("inputcolumn")
                                    },
                                    showTopAppBar = showTopAppBar,
                                    showBottomNav = showBottomNav,
                                    showFooter = showFooter,
                                )
                            }
                            composable(route = "column") {
                                ColumnScreen(
                                    backPressed = {
                                        navController.popBackStack()
                                    },
                                    showTopAppBar = showTopAppBar,
                                    showBottomNav = showBottomNav,
                                    showFooter = showFooter,
                                )
                            }
                            composable(route = "lazycolumn") {
                                LazyColumnScreen(
                                    backPressed = {
                                        navController.popBackStack()
                                    },
                                    showTopAppBar = showTopAppBar,
                                    showBottomNav = showBottomNav,
                                    showFooter = showFooter,
                                )
                            }
                            composable(route = "inputcolumn") {
                                InputFormWithFooterScreen(
                                    backPressed = {
                                        navController.popBackStack()
                                    },
                                    showTopAppBar = showTopAppBar,
                                    showBottomNav = showBottomNav,
                                    showFooter = showFooter,
                                )
                            }
                        }

                        DemoActionsFab(
                            modifier = Modifier.align(Alignment.TopEnd),
                            expanded = actionsMenuExpanded,
                            showTopAppBar = showTopAppBar,
                            showFooter = showFooter,
                            showBottomNav = showBottomNav,
                            onExpandedChange = {
                                actionsMenuExpanded = it
                            },
                            onToggleTopAppBar = {
                                showTopAppBar = !showTopAppBar
                            },
                            onToggleFooter = {
                                showFooter = !showFooter
                            },
                            onToggleBottomNav = {
                                showBottomNav = !showBottomNav
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DemoActionsFab(
    expanded: Boolean,
    showTopAppBar: Boolean,
    showFooter: Boolean,
    showBottomNav: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onToggleTopAppBar: () -> Unit,
    onToggleFooter: () -> Unit,
    onToggleBottomNav: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    Box(
        modifier = modifier.padding(top = statusBarHeight + 32.dp, end = 16.dp),
    ) {
        SmallFloatingActionButton(
            onClick = {
                onExpandedChange(true)
            },
        ) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "Open demo actions",
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                onExpandedChange(false)
            },
        ) {
            ToggleMenuItem(
                text = if (showTopAppBar) "Hide top app bar" else "Show top app bar",
                onClick = {
                    onToggleTopAppBar()
                    onExpandedChange(false)
                },
            )

            ToggleMenuItem(
                text = if (showFooter) "Hide footer" else "Show footer",
                onClick = {
                    onToggleFooter()
                    onExpandedChange(false)
                },
            )

            ToggleMenuItem(
                text = if (showBottomNav) {
                    "Hide bottom navigation"
                } else {
                    "Show bottom navigation"
                },
                onClick = {
                    onToggleBottomNav()
                    onExpandedChange(false)
                },
            )
        }
    }
}

@Composable
private fun ToggleMenuItem(
    text: String,
    onClick: () -> Unit,
) {
    DropdownMenuItem(
        text = {
            Text(text)
        },
        onClick = onClick,
    )
}
