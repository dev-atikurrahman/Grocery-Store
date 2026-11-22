package com.atik.grocerystore.feature.onboarding.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.atik.grocerystore.R
import com.atik.grocerystore.components.OnboardingButton
import com.atik.grocerystore.components.OnboardingButtonText

@Composable
fun OnboardingBottomBar(
    isLastPage: Boolean,
    onSkipClick: () -> Unit,
    onNextClick: () -> Unit,
    onGetStartedClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(
            visible = !isLastPage,
            enter = fadeIn() + expandHorizontally(),
            exit = fadeOut() + shrinkHorizontally()
        ) {
            TextButton(
                onClick = onSkipClick,
                modifier = modifier.height(56.dp)
            ) {
                OnboardingButtonText(
                    text = stringResource(R.string.onboarding_skip),
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }

        OnboardingButton(
            text = stringResource(
                if (isLastPage) R.string.get_started else R.string.onboarding_next
            ),
            onClick = if (isLastPage) onGetStartedClick else onNextClick,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .then(if (isLastPage) Modifier.weight(1f) else Modifier.wrapContentWidth())
                .animateContentSize()
                .height(56.dp)
        )
    }


}