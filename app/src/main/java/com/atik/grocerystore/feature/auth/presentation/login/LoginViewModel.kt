package com.atik.grocerystore.feature.auth.presentation.login

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atik.grocerystore.feature.auth.data.preferences.UserPreferences
import com.atik.grocerystore.feature.auth.domain.usecase.ValidateEmail
import com.atik.grocerystore.feature.auth.domain.usecase.ValidateLoginPassword
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userPreferences: UserPreferences,
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidateLoginPassword = ValidateLoginPassword()
) : ViewModel() {
    companion object {
        const val AUTH_TAG = "AUTH_DEBUG"
    }

    val emailState = TextFieldState()
    val passwordState = TextFieldState()

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _effects = Channel<LoginEffect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    init {
        viewModelScope.launch {
            snapshotFlow { emailState.text.toString() }
                .drop(1)
                .collect {
                    _uiState.update { s -> s.copy(emailError = null, generalError = null) }
                }

            snapshotFlow { passwordState.text.toString() }
                .drop(1)
                .collect {
                    _uiState.update { s -> s.copy(emailError = null, generalError = null) }
                }
        }
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.RememberMeChanged ->
                _uiState.update { it.copy(rememberMe = event.value) }

            LoginEvent.ErrorDismissed ->
                _uiState.update { it.copy(generalError = null) }

            LoginEvent.SubmitClicked -> submit()
        }
    }

    private fun submit() {
        if (_uiState.value.isLoading) return

        val email = emailState.text.toString().trim()
        val password = passwordState.text.toString()

        val emailResult = validateEmail.execute(email)
        val passwordResult = validatePassword.execute(password)

        if (!emailResult.successful || !passwordResult.successful) {
            _uiState.update {
                it.copy(
                    emailError = emailResult.errorMessage,
                    passwordError = passwordResult.errorMessage
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, generalError = null) }

            // TODO:

            _uiState.update { it.copy(isLoading = false) }
        }

    }


}