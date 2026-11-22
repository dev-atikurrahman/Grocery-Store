package com.atik.grocerystore.feature.onboarding.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.atik.grocerystore.R
import com.atik.grocerystore.components.OnboardingButton

@Composable
fun OnboardingBottomBar(
    isLastPage: Boolean,
    onNextClick: () -> Unit,
    onGetStartedClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    OnboardingButton(
        text = stringResource(if (isLastPage) R.string.get_started else R.string.onboarding_next),
        onClick = if (isLastPage) onGetStartedClick else onNextClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
            .height(48.dp)
    )

}