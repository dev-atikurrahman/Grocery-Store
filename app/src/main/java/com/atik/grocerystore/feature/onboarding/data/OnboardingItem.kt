package com.atik.grocerystore.feature.onboarding.data


import androidx.annotation.DrawableRes
import com.atik.grocerystore.R

sealed class OnboardingItem(
    @DrawableRes val imageRes: Int, val title: String, val description: String
)


data object FirstPage : OnboardingItem(
    imageRes = R.drawable.ic_launcher_background,
    title = "Manage your store\nwith ease",
    description = "Keep your grocery business organized\nand manage everything in one place."
)

data object SecondPage : OnboardingItem(
    imageRes = R.drawable.ic_launcher_foreground,
    title = "Track sales and\nmanage inventory",
    description = "Monitor your daily sales, products\nand stock levels effortlessly."
)

data object ThirdPage : OnboardingItem(
    imageRes = R.drawable.ic_launcher_background,
    title = "Stay in control\nof your business",
    description = "Get insights into your business\nand make smarter decisions."
)

