package com.atik.grocerystore.navigation.bottombar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.material.icons.rounded.AddBox
import androidx.compose.material.icons.rounded.Dashboard
import androidx.compose.material.icons.rounded.Groups
import androidx.compose.material.icons.rounded.Sell
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atik.grocerystore.R
import com.atik.grocerystore.VerticalSpacer
import com.atik.grocerystore.components.AppText
import com.atik.grocerystore.navigation.Routes
import com.atik.grocerystore.ui.theme.GlassTextPrimary

@Composable
fun BottomNavigationBar(
    items: List<NavigationItem>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {


    val cs = MaterialTheme.colorScheme
    val black = cs.onBackground


    val activeBlue = Color(0xFF007AFF) // iOS-style blue
    val inactiveColor = Color(0xFF1C1C1E).copy(alpha = 0.85f)

    val glassGradient = Brush.verticalGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.42f),
            Color.White.copy(alpha = 0.22f)
        )
    )
    val glassBorderBrush = Brush.linearGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.75f),
            Color.White.copy(alpha = 0.25f),
            Color.White.copy(alpha = 0.55f)
        )
    )


    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .height(68.dp)
            .shadow(
                elevation = 12.dp,
                shape = CircleShape,
                ambientColor = black.copy(alpha = 0.15f),
                spotColor = black.copy(alpha = 0.2f)
            )
            .clip(RoundedCornerShape(50.dp))
            .background(brush = glassGradient)
            .border(width = 1.dp, brush = glassBorderBrush, shape = CircleShape)
            .padding(4.dp),
    ) {
        val tabWidth = maxWidth / items.size

        val indicatorOffset by animateDpAsState(
            targetValue = tabWidth * selectedIndex,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessMediumLow
            ),
            label = "indicatorOffset"
        )

        Box(
            modifier = Modifier
                .offset(x = indicatorOffset)
                .width(tabWidth)
                .fillMaxHeight()
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.92f))
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, item ->
                    val isSelected = index == selectedIndex

                    val iconColor by animateColorAsState(
                        targetValue = if (isSelected) activeBlue else inactiveColor,
                        label = "iconColor"
                    )
                    val textColor by animateColorAsState(
                        targetValue = if (isSelected) GlassTextPrimary else inactiveColor,
                        label = "textColor"
                    )
                    Column(
                        modifier = Modifier
                            .width(tabWidth)
                            .fillMaxHeight()
                            .clip(CircleShape)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) { onTabSelected(index) },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = if (isSelected) item.selectedIcon else item.unSelectedIcon,
                            contentDescription = item.label,
                            tint = iconColor,
                            modifier = Modifier.size(24.dp)
                        )
                        VerticalSpacer(size = 3.dp)
                        AppText(
                            text = item.label,
                            color = textColor,
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                            maxLines = 1
                        )
                    }
                }


            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GlassBottomBarPreview() {
    var selectedTab by remember { mutableIntStateOf(0) }

    val navigationItem = listOf(
        NavigationItem(
            label = stringResource(R.string.home),
            route = Routes.Home.route,
            selectedIcon = Icons.Rounded.Dashboard,
            unSelectedIcon = Icons.Outlined.Dashboard,
        ),
        NavigationItem(
            label = stringResource(R.string.sales),
            route = Routes.Sales.route,
            selectedIcon = Icons.Rounded.Sell,
            unSelectedIcon = Icons.Outlined.Sell,
            badgeCount = 4
        ),
        NavigationItem(
            label = stringResource(R.string.products),
            route = Routes.Products.route,
            selectedIcon = Icons.Rounded.AddBox,
            unSelectedIcon = Icons.Outlined.AddBox,
        ),
        NavigationItem(
            label = stringResource(R.string.customers),
            route = Routes.Customers.route,
            selectedIcon = Icons.Rounded.Groups,
            unSelectedIcon = Icons.Outlined.Groups,
        )
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = navigationItem,
                selectedIndex = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { innerPadding ->
        // Sample gradient background matching the screenshot
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF56B4E9),
                            Color(0xFF7D858C)
                        )
                    )
                )
                .padding(innerPadding)
        )
    }
}








