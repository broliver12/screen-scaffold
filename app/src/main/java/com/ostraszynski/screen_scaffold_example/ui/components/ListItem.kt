package com.ostraszynski.screen_scaffold_example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ListItem(value: Int) {

    val bgColor = if(value % 2 == 0) Color.LightGray else Color.DarkGray
    val textColor = if(value % 2 == 0) Color.Black else Color.White

    Box(
        modifier = Modifier.background(bgColor).fillMaxWidth().height(48.dp)
    ) {
        Text(
            text = value.toString(),
            color = textColor
        )
    }
}