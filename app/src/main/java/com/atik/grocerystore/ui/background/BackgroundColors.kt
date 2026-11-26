package com.atik.grocerystore.ui.background


import androidx.compose.ui.graphics.Color

object BackgroundColors {

    // Pastel Palette
    val PastelPink = Color(0xFFFFB8D9)
    val PastelPurple = Color(0xFFD8A6FF)
    val PastelLavender = Color(0xFFE8DFFF)
    val PastelBlue = Color(0xFF9CCBFF)
    val PastelPeach = Color(0xFFF8E5D8)

    // Base Colors
    val SoftWhite = Color(0xFFF9F7FC)
    val SoftLavender = Color(0xFFF1EBFA)

    // Glow Colors
    val PinkGlow = Color(0xFFFFB8E2)
    val PurpleGlow = Color(0xFFDDA6FF)
    val BlueGlow = Color(0xFF9CCBFF)

    // Gradient Palettes
    val PastelGradient = listOf(
        PastelPink,
        PastelLavender,
        PastelBlue
    )

    val PeachGradient = listOf(
        PastelPeach,
        PastelPink,
        PastelLavender
    )
}