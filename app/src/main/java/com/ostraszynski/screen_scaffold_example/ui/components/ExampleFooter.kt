package com.ostraszynski.screen_scaffold_example.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ExampleFooter() {
    val context = LocalContext.current
    Box(
        modifier = Modifier.background(
            Color.LightGray
        ).fillMaxWidth().height(64.dp).padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                Toast.makeText(
                    context,
                    "Hello from footer",
                    Toast.LENGTH_SHORT
                ).show()
            }
        ) {
            Text("Show Toast")
        }
    }
}