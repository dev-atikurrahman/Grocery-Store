package com.atik.grocerystore.feature.auth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.atik.grocerystore.ui.theme.GlassBackground
import com.atik.grocerystore.ui.theme.GlassBorderLight
import com.atik.grocerystore.ui.theme.GlassShadow

@Composable
fun GlassCardContainer(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .background(GlassBackground)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        GlassBorderLight,
                        GlassShadow,
                        GlassBorderLight.copy(alpha = 0.12f)
                    )
                ),
                shape = RoundedCornerShape(32.dp)
            )
            .padding(horizontal = 24.dp, vertical = 28.dp),
        content = content
    )
}