package com.atik.grocerystore.feature.auth.di

import com.atik.grocerystore.feature.auth.presentation.login.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel { LoginViewModel(userPreferences = get()) }
}