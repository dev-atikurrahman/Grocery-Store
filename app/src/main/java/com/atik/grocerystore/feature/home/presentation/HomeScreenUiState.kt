package com.atik.grocerystore.feature.home.presentation

import com.atik.grocerystore.feature.auth.presentation.login.LoginEvent

data class HomeScreenUiState(
    val isLoading: Boolean = false,
    val generalError: String? = null
)

sealed interface HomeScreenEvent {
    data object ErrorDismissed : HomeScreenEvent
}

