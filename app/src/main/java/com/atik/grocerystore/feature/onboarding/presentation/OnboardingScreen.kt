package com.atik.grocerystore.feature.onboarding.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.atik.grocerystore.R
import com.atik.grocerystore.VerticalSpacer
import com.atik.grocerystore.components.MenuText
import com.atik.grocerystore.feature.onboarding.data.OnboardingPage
import com.atik.grocerystore.feature.onboarding.data.onboardingPages
import com.atik.grocerystore.feature.onboarding.presentation.components.OnboardingBottomBar
import com.atik.grocerystore.feature.onboarding.presentation.components.OnboardingPageContent
import com.atik.grocerystore.feature.onboarding.presentation.components.PageIndicator
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.absoluteValue


@Composable
fun OnboardingScreen(
    onFinished: () -> Unit,
    modifier: Modifier = Modifier,
    pages: List<OnboardingPage> = onboardingPages,
    viewModel: OnboardingViewModel = koinViewModel()
) {

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()
    val isLastPage by remember { derivedStateOf { pagerState.currentPage == pages.lastIndex } }

    val finishOnboarding: () -> Unit = { viewModel.completeOnboarding(onFinished) }

    BackHandler(enabled = pagerState.currentPage > 0) {
        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(visible = !isLastPage) {
                TextButton(onClick = finishOnboarding) {
                    MenuText(
                        text = stringResource(R.string.onboarding_skip),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { index ->
            val pageOffset =
                ((pagerState.currentPage - index) + pagerState.currentPageOffsetFraction).absoluteValue

            OnboardingPageContent(
                page = pages[index],
                modifier = Modifier.graphicsLayer {
                    alpha = 1f - pageOffset.coerceIn(0f, 1f) * 0.6f
                }
            )
        }
        VerticalSpacer(size = 12.dp)
        PageIndicator(pageCount = pages.size, pagerState.currentPage)

        OnboardingBottomBar(
            isLastPage = isLastPage,
            onNextClick = {
                scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
            },
            onGetStartedClick = finishOnboarding
        )
    }

}