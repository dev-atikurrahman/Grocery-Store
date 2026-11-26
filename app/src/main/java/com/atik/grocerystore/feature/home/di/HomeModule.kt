package com.atik.grocerystore.feature.home.di

import com.atik.grocerystore.feature.auth.presentation.login.LoginViewModel
import com.atik.grocerystore.feature.home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel() }
}