package com.atik.grocerystore.feature.onboarding.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.atik.grocerystore.VerticalSpacer
import com.atik.grocerystore.components.DescriptionText
import com.atik.grocerystore.components.OnboardingDescription
import com.atik.grocerystore.components.OnboardingTitle
import com.atik.grocerystore.feature.onboarding.data.OnboardingPage

@Composable
fun OnboardingPageContent(
    page: OnboardingPage,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(page.imageRes),
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .background(MaterialTheme.colorScheme.primary),
            contentScale = ContentScale.Fit
        )


        VerticalSpacer(size = 24.dp)
        OnboardingTitle(
            text = stringResource(page.titleRes),
            color = MaterialTheme.colorScheme.onBackground
        )

        VerticalSpacer(size = 8.dp)
        OnboardingDescription(
            text = stringResource(page.descriptionRes),
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        VerticalSpacer(size = 32.dp)

    }

}