package com.example.nasapictures.ui.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.verticalGradientBackground(colors: List<Color>) = drawWithCache {
    onDrawBehind {
        drawRect(
            Brush.verticalGradient(
                colors = colors,
                startY = 0f,
                endY = size.height
            )
        )
    }
}
