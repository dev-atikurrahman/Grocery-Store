package com.atik.grocerystore.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.atik.grocerystore.R

val plushJakartaSans = FontFamily(
    Font(resId = R.font.regular_font, weight = FontWeight.Normal),
    Font(resId = R.font.medium_font, weight = FontWeight.Medium),
    Font(resId = R.font.semibold_font, weight = FontWeight.SemiBold),
    Font(resId = R.font.bold_font, weight = FontWeight.Bold),
)

private object OnboardingTextStyle {

    val Title = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = plushJakartaSans
    )

    val Description = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = plushJakartaSans
    )

    val Button = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = plushJakartaSans
    )

}

@Composable
fun OnboardingTitle(
    text: String,
    modifier: Modifier = Modifier,
    color: Color,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        modifier = modifier,
        style = OnboardingTextStyle.Title,
        color = color,
        maxLines = maxLines,
        overflow = overflow
    )
}

@Composable
fun OnboardingDescription(
    text: String,
    modifier: Modifier = Modifier,
    color: Color,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        modifier = modifier,
        style = OnboardingTextStyle.Description,
        color = color,
        maxLines = maxLines,
        overflow = overflow
    )
}

@Composable
fun OnboardingButtonText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        modifier = modifier,
        style = OnboardingTextStyle.Button,
        color = color,
        maxLines = maxLines,
        overflow = overflow
    )
}