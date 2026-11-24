package com.atik.grocerystore.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.unit.dp
import com.atik.grocerystore.components.GlassDefaults.HighlightColor
import com.atik.grocerystore.ui.theme.GlassFill
import com.atik.grocerystore.ui.theme.GlassPillFill
import com.kyant.backdrop.Backdrop
import com.kyant.backdrop.drawBackdrop
import com.kyant.backdrop.effects.blur
import com.kyant.backdrop.effects.lens
import com.kyant.backdrop.highlight.Highlight
import com.kyant.backdrop.highlight.HighlightStyle

@Composable
fun GlassPill(
    backdrop: Backdrop,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {

    val shape = RoundedCornerShape(50.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .drawBackdrop(
                backdrop = backdrop,
                shape = { shape },
                effects = {
                    blur(8.dp.toPx())
                    lens(
                        refractionHeight = 8.dp.toPx(),
                        refractionAmount = 12.dp.toPx(),
                        depthEffect = true
                    )
                },
                highlight = {
                    Highlight.Ambient.copy(
                        width = 1.dp,
                        blurRadius = 2.dp,
                        alpha = 1f,
                        style = HighlightStyle.Default(
                            color = HighlightColor,
                            blendMode = BlendMode.Plus,
                            angle = 45f,
                            falloff = 1f
                        )
                    )
                },
                onDrawSurface = { drawRect(GlassPillFill) },
            )
            .padding(horizontal = 6.dp),
        contentAlignment = Alignment.CenterStart,
        content = content
    )

}