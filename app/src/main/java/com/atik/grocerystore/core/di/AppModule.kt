package com.atik.grocerystore.core.di

import com.atik.grocerystore.feature.splash.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SplashViewModel(userPreferences = get()) }
}