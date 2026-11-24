package com.atik.grocerystore.feature.auth.presentation.login

data class LoginUiState(
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val rememberMe: Boolean = false,
    val generalError: String? = null
)

/** Sent from the UI to the ViewModel */
sealed interface LoginEvent {
    data class RememberMeChanged(val value: Boolean) : LoginEvent
    data object SubmitClicked : LoginEvent
    data object ErrorDismissed : LoginEvent
}

/** Sent from the ViewModel to the UI as a one-time event */
sealed interface LoginEffect {
    data object LoginSuccess : LoginEffect
}