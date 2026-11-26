package com.atik.grocerystore.ui.background

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush

@Composable
fun GradientBackground(
    brush: Brush,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope. () -> Unit
) {
    Box(
        modifier = modifier
            .background(brush)
    ) {
        content()
    }

}

/*
GradientBackground(
brush = Brush.linearGradient(
colors = BackgroundColors.PastelGradient
),
modifier = Modifier.fillMaxSize()
) {
    // Your screen content
}*/
