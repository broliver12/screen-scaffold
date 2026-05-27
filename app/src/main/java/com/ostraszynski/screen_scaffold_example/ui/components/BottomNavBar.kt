package com.ostraszynski.screen_scaffold_example.ui.components

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext

@Composable
fun BottomNavBar() {
    val context = LocalContext.current
    val tabs = listOf(
        BottomNavTab(
            label = "Home",
            icon = Icons.Filled.Home,
        ),
        BottomNavTab(
            label = "List",
            icon = Icons.AutoMirrored.Filled.List,
        ),
        BottomNavTab(
            label = "Form",
            icon = Icons.Filled.Edit,
        ),
        BottomNavTab(
            label = "Profile",
            icon = Icons.Filled.AccountCircle,
        ),
    )

    NavigationBar {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                selected = index == 0,
                onClick = {
                    Toast.makeText(context, "${tab.label} clicked", Toast.LENGTH_SHORT).show()
                },
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.label,
                    )
                },
                label = {
                    Text(tab.label)
                },
            )
        }
    }
}

private data class BottomNavTab(
    val label: String,
    val icon: ImageVector,
)
