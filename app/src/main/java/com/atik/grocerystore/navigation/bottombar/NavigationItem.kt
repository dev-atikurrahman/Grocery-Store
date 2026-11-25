package com.atik.grocerystore.navigation.bottombar

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val label: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val badgeCount: Int? = null
)

