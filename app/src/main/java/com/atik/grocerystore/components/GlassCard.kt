package com.atik.grocerystore.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.atik.grocerystore.ui.theme.GlassBackdropFallback
import com.atik.grocerystore.ui.theme.GlassFill
import com.atik.grocerystore.ui.theme.GlassLightStrong
import com.kyant.backdrop.Backdrop
import com.kyant.backdrop.backdrops.layerBackdrop
import com.kyant.backdrop.backdrops.rememberLayerBackdrop
import com.kyant.backdrop.drawBackdrop
import com.kyant.backdrop.effects.blur
import com.kyant.backdrop.effects.lens
import com.kyant.backdrop.effects.vibrancy
import com.kyant.backdrop.highlight.Highlight
import com.kyant.backdrop.highlight.HighlightStyle


object GlassDefaults {
    val CornerRadius = 36.dp
    val Blur = 24.dp                 // Figma "Frost"
    val RefractionHeight = 12.dp     // Figma "Refraction"
    val RefractionAmount = 32.dp
    val ContentPadding = PaddingValues(horizontal = 24.dp, vertical = 28.dp)
    val Tint = GlassFill             // 404C42 @ 30%
    val HighlightColor = GlassLightStrong
    const val HighlightAlpha = 0.8f
    const val HighlightAngle = 45f
}

@Composable
fun GlassCard(
    backdrop: Backdrop,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = GlassDefaults.CornerRadius,
    blurRadius: Dp = GlassDefaults.Blur,
    refractionHeight: Dp = GlassDefaults.RefractionHeight,
    refractionAmount: Dp = GlassDefaults.RefractionAmount,
    depthEffect: Boolean = true,
    tint: Color = GlassDefaults.Tint,
    highlightColor: Color = GlassDefaults.HighlightColor,
    highlightAlpha: Float = GlassDefaults.HighlightAlpha,
    highlightAngle: Float = GlassDefaults.HighlightAngle,
    contentPadding: PaddingValues = GlassDefaults.ContentPadding,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable ColumnScope.() -> Unit
) {
    val shape = RoundedCornerShape(cornerRadius)
    val safeRefractionHeight = refractionHeight.coerceIn(0.dp, cornerRadius)

    Column(
        modifier = modifier
            .drawBackdrop(
                backdrop = backdrop,
                shape = { shape },
                effects = {
                    vibrancy()
                    blur(blurRadius.toPx())
                    lens(
                        refractionHeight = safeRefractionHeight.toPx(),
                        refractionAmount = refractionAmount.toPx(),
                        depthEffect = depthEffect
                    )
                },
                highlight = {
                    Highlight.Ambient.copy(
                        width = 1.dp,
                        blurRadius = 2.dp,
                        alpha = highlightAlpha,
                        style = HighlightStyle.Default(
                            color = highlightColor,
                            blendMode = BlendMode.Plus,
                            angle = highlightAngle,
                            falloff = 1f
                        )
                    )
                },
                onDrawSurface = { drawRect(tint) }
            )
            .padding(contentPadding),
        verticalArrangement = verticalArrangement,
        content = content
    )
}


@Composable
fun GlassBackground(
    backgroundRes: Int,
    modifier: Modifier = Modifier,
    fallbackColor: Color = GlassBackdropFallback,
    content: @Composable BoxScope.(backdrop: Backdrop) -> Unit
) {
    val backdrop = rememberLayerBackdrop {
        drawRect(fallbackColor)
        drawContent()
    }

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(backgroundRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .layerBackdrop(backdrop)
        )
        content(backdrop)
    }
}