package com.atik.grocerystore.ui.background

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class Glow(
    val color: Color,
    val centerX: Float,
    val centerY: Float,
    val radius: Float,
    val alpha: Float = 0.5f
)

@Composable
fun GlowBackground(
    modifier: Modifier = Modifier,
    baseColor: Color = Color.Transparent,
    baseBrush: Brush? = null,
    glows: List<Glow>,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier.background(baseColor).then(
            if (baseBrush != null) {
                Modifier.background(baseBrush)
            } else {
                Modifier
            }
        )
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            glows.forEach { glow ->
                drawRect(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            glow.color.copy(alpha = glow.alpha),
                            Color.Transparent
                        ),
                        center = Offset(
                            x = size.width * glow.centerX,
                            y = size.height * glow.centerY
                        ),
                        radius = size.minDimension * glow.radius
                    )
                )
            }
        }

        content()
    }
}