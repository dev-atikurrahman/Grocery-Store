package com.atik.grocerystore.navigation.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.atik.grocerystore.components.AppText
import com.atik.grocerystore.components.GlassCard
import com.atik.grocerystore.ui.theme.BottomBarTint
import com.atik.grocerystore.ui.theme.OnPrimaryLight
import com.kyant.backdrop.Backdrop
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

val BottomBarContentPadding = 100.dp

private object BottomBarDefaults {
    val Height = 68.dp
    val HorizontalMargin = 20.dp
    val BottomMargin = 16.dp
    val InnerPadding = 4.dp
    val IndicatorExtra = 8.dp
    val CornerRadius = 50.dp
    val IconSize = 26.dp
    const val DraggingScale = 1.12f
}

private fun <T> bottomBarSpring() = spring<T>(
    dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessMediumLow
)

@Composable
fun BottomNavigationBarSection(
    showBottomBar: Boolean,
    navController: NavController,
    items: List<NavigationItem>,
    selectedIndex: Int,
    backdrop: Backdrop,
    modifier: Modifier = Modifier,
    onTabSelected: ((Int) -> Unit)? = null
) {
    AnimatedVisibility(
        visible = showBottomBar, enter = slideInVertically(
            initialOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
        ) + fadeIn(animationSpec = tween(300)), exit = slideOutVertically(
            targetOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
        ) + fadeOut(animationSpec = tween(200)), modifier = modifier
    ) {
        BottomNavigationBar(
            items = items,
            selectedIndex = selectedIndex,
            backdrop = backdrop,
            onTabSelected = { index ->
                if (onTabSelected != null) {
                    onTabSelected(index)
                } else {
                    val targetRoute = items.getOrNull(index)?.route
                    if (targetRoute != null) {
                        navController.navigate(targetRoute) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            })
    }
}

@Composable
fun BottomNavigationBar(
    items: List<NavigationItem>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    backdrop: Backdrop,
    modifier: Modifier = Modifier
) {
    GlassCard(
        backdrop = backdrop,
        modifier = modifier
            .navigationBarsPadding()
            .padding(
                start = BottomBarDefaults.HorizontalMargin,
                end = BottomBarDefaults.HorizontalMargin,
                bottom = BottomBarDefaults.BottomMargin
            )
            .fillMaxWidth()
            .height(BottomBarDefaults.Height),
        cornerRadius = BottomBarDefaults.CornerRadius,
        blurRadius = 40.dp,
        refractionHeight = 16.dp,
        refractionAmount = 12.dp,
        depthEffect = true,
        tint = BottomBarTint,
        highlightColor = OnPrimaryLight.copy(alpha = 0.75f),
        highlightWidth = 0.75.dp,
        highlightAngle = 45f,
        highlightAlpha = 0.50f,
        highlightFalloff = 3f,
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.Center
    ) {
        BottomBarTabs(
            items = items, selectedIndex = selectedIndex, onTabSelected = onTabSelected
        )
    }
}

@Composable
fun BottomBarTabs(
    items: List<NavigationItem>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
) {
    val density = LocalDensity.current
    val haptic = LocalHapticFeedback.current
    val scope = rememberCoroutineScope()
    val currentSelected by rememberUpdatedState(selectedIndex)
    val currentOnTabSelected by rememberUpdatedState(onTabSelected)

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(BottomBarDefaults.InnerPadding),
    ) {
        val extra = BottomBarDefaults.IndicatorExtra

        val tabWidth = (maxWidth - extra * 2) / items.size
        val indicatorWidth = tabWidth + extra * 2

        val tabWidthPx = with(density) { tabWidth.toPx() }
        val extraPx = with(density) { extra.toPx() }
        val lastIndex = items.lastIndex
        val maxOffsetPx = tabWidthPx * lastIndex

        fun offsetFor(index: Int) = tabWidthPx * index
        fun indexAt(offset: Float) = (offset / tabWidthPx).roundToInt().coerceIn(0, lastIndex)

        val offsetAnim = remember { Animatable(offsetFor(selectedIndex)) }
        var isDragging by remember { mutableStateOf(false) }

        val dragIndex by remember(tabWidthPx, items.size) {
            derivedStateOf { indexAt(offsetAnim.value) }
        }
        val highlightIndex = if (isDragging) dragIndex else selectedIndex

        LaunchedEffect(selectedIndex, tabWidthPx) {
            if (!isDragging) {
                offsetAnim.animateTo(
                    offsetFor(selectedIndex), bottomBarSpring()
                )
            }
        }

        LaunchedEffect(dragIndex) {
            if (isDragging) haptic.performHapticFeedback(HapticFeedbackType.SegmentTick)
        }

        val indicatorScale by animateFloatAsState(
            targetValue = if (isDragging) BottomBarDefaults.DraggingScale else 1f,
            animationSpec = spring(stiffness = Spring.StiffnessMedium),
            label = "indicatorScale"
        )

        // indicator
        Box(
            modifier = Modifier
                .offset { IntOffset(offsetAnim.value.roundToInt(), 0) }
                .width(indicatorWidth)
                .fillMaxHeight()
                .graphicsLayer {
                    scaleX = indicatorScale
                    scaleY = indicatorScale
                }
                .clip(CircleShape)
                .background(OnPrimaryLight.copy(alpha = 0.14f))
                .border(0.75.dp, OnPrimaryLight.copy(alpha = 0.25f), CircleShape))

        // Tabs
        Row(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(tabWidthPx, extraPx, items.size) {
                    // tap
                    detectTapGestures { pos ->
                        val index = ((pos.x - extraPx) / tabWidthPx).toInt().coerceIn(0, lastIndex)
                        currentOnTabSelected(index)
                    }
                }
                .pointerInput(tabWidthPx, items.size) {
                    // drag
                    detectHorizontalDragGestures(
                        onDragStart = { isDragging = true },
                        onHorizontalDrag = { change, dragAmount ->
                            change.consume()
                            scope.launch {
                                offsetAnim.snapTo(
                                    (offsetAnim.value + dragAmount).coerceIn(0f, maxOffsetPx)
                                )
                            }
                        },
                        onDragEnd = {
                            val target = indexAt(offsetAnim.value)
                            isDragging = false
                            scope.launch {
                                offsetAnim.animateTo(
                                    offsetFor(target), bottomBarSpring()
                                )
                            }
                            if (target != currentSelected) currentOnTabSelected(target)
                        },
                        onDragCancel = {
                            isDragging = false
                            scope.launch {
                                offsetAnim.animateTo(
                                    offsetFor(currentSelected), bottomBarSpring()
                                )
                            }
                        })
                }
                .padding(horizontal = extra),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                BottomBarItem(
                    item = item,
                    selected = index == highlightIndex,
                    onClick = { currentOnTabSelected(index) },
                    modifier = Modifier.width(tabWidth)
                )
            }


        }
    }

}


@Composable
private fun BottomBarItem(
    item: NavigationItem,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val iconColor by animateColorAsState(
        targetValue = OnPrimaryLight.copy(alpha = if (selected) 0.90f else 0.50f),
        label = "iconColor"
    )
    val textColor by animateColorAsState(
        targetValue = if (selected) OnPrimaryLight else OnPrimaryLight.copy(alpha = 0.50f),
        label = "textColor"
    )

    Column(
        modifier = modifier
            .fillMaxHeight()
            .semantics {
                role = Role.Tab
                this.selected = selected
                onClick { onClick(); true }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = if (selected) item.selectedIcon else item.unSelectedIcon,
            contentDescription = null,           // label-ই বর্ণনা দিচ্ছে
            tint = iconColor,
            modifier = Modifier.size(BottomBarDefaults.IconSize)
        )
        AppText(
            text = item.label,
            color = textColor,
            fontSize = 12.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
            maxLines = 1,
            softWrap = false
        )
    }
}




