package com.atik.grocerystore.ui.background

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun PastelBackground(
    modifier: Modifier = Modifier,
    baseGradient: List<Color> =
        BackgroundColors.PastelGradient,
    content: @Composable BoxScope.() -> Unit
) {
    GlowBackground(
        modifier = modifier,
        baseBrush = Brush.linearGradient(
            colors = baseGradient
        ),
        glows = listOf(
            Glow(
                color = BackgroundColors.PurpleGlow,
                centerX = 0.15f,
                centerY = 0.15f,
                radius = 0.65f,
                alpha = 0.55f
            ),
            Glow(
                color = BackgroundColors.PinkGlow,
                centerX = 0.75f,
                centerY = 0.45f,
                radius = 0.65f,
                alpha = 0.45f
            ),
            Glow(
                color = BackgroundColors.BlueGlow,
                centerX = 0.9f,
                centerY = 0.95f,
                radius = 0.55f,
                alpha = 0.45f
            )
        ),
        content = content
    )
}