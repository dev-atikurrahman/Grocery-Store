package com.atik.grocerystore.feature.onboarding.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.atik.grocerystore.VerticalSpacer
import com.atik.grocerystore.components.DescriptionText
import com.atik.grocerystore.components.OnboardingHeading
import com.atik.grocerystore.components.TitleText
import com.atik.grocerystore.feature.onboarding.data.OnboardingItem

@Composable
fun OnboardingPageContent(
    page: OnboardingItem,
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(page.imageRes),
            contentDescription = page.title,
            modifier = modifier
                .fillMaxWidth()
                .height(320.dp),
            contentScale = ContentScale.Fit
        )


        VerticalSpacer(size = 24.dp)
        TitleText(
            text = page.title,
            color = MaterialTheme.colorScheme.onBackground
        )

        VerticalSpacer(size = 12.dp)
        DescriptionText(
            text = page.description,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        VerticalSpacer(size = 24.dp)
        PageIndicator(
            pageCount = pageCount,
            currentPage = currentPage
        )
    }

}