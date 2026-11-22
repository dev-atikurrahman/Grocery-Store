package com.atik.grocerystore.feature.onboarding.di

import com.atik.grocerystore.feature.onboarding.presentation.OnboardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val onboardingModule = module {
    viewModel { OnboardingViewModel(userPreferences = get()) }
}