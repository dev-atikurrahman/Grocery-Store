package com.atik.grocerystore.feature.onboarding.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.atik.grocerystore.feature.onboarding.data.OnboardingItem
import com.atik.grocerystore.feature.onboarding.data.onboardingPages
import com.atik.grocerystore.feature.onboarding.presentation.components.OnboardingBottomBar
import com.atik.grocerystore.feature.onboarding.presentation.components.OnboardingPageContent
import kotlinx.coroutines.launch


@Composable
fun OnboardingScreen(
    pages: List<OnboardingItem> = onboardingPages,
    onFinishOnboarding: () -> Unit
) {

    val pagerState = rememberPagerState(
        pageCount = { pages.size }
    )
    val coroutineScope = rememberCoroutineScope()
    val isLastPage = pagerState.currentPage == pages.lastIndex


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(horizontal = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) { pageIndex ->

            OnboardingPageContent(
                page = pages[pageIndex],
                pageCount = pages.size,
                currentPage = pagerState.currentPage
            )

        }

        OnboardingBottomBar(
            isLastPage = isLastPage,
            onSkipClick = onFinishOnboarding,
            onNextClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(
                        pagerState.currentPage + 1
                    )
                }
            },
            onStartedClick = onFinishOnboarding
        )
    }

}