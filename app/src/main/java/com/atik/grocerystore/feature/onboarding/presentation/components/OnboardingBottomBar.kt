package com.atik.grocerystore.feature.onboarding.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atik.grocerystore.R
import com.atik.grocerystore.components.AppText
import com.atik.grocerystore.components.OnboardingButton

@Composable
fun OnboardingBottomBar(
    isLastPage: Boolean,
    onSkipClick: () -> Unit,
    onNextClick: () -> Unit,
    onStartedClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = if (isLastPage) Arrangement.Center else Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (!isLastPage) {
            TextButton(onClick = onSkipClick) {
                AppText(
                    text = "Skip",
                    color = MaterialTheme.colorScheme.primary,
                    style = TextStyle(
                        fontSize = 12.sp,
                        letterSpacing = 1.sp,
                    )
                )
            }

            OnboardingButton(
                text = "Next",
                onClick = onNextClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .wrapContentWidth()
                    .height(48.dp)
            )
        } else {
            OnboardingButton(
                text = "Get Started",
                onClick = onStartedClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            )
        }
    }
}