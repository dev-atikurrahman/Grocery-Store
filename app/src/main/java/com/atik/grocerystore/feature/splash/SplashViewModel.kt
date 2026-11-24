package com.atik.grocerystore.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atik.grocerystore.feature.auth.data.preferences.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


sealed interface StartDestination {
    data object Loading : StartDestination
    data object Login : StartDestination
    data object Home : StartDestination
}

class SplashViewModel(
    userPreferences: UserPreferences
) : ViewModel() {
    private val _startDestination = MutableStateFlow<StartDestination>(StartDestination.Loading)
    val startDestination: StateFlow<StartDestination> = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            userPreferences.clearSessionIfNotRemembered()
            _startDestination.value =
                if (userPreferences.isLoggedIn.first()) StartDestination.Home
                else StartDestination.Login
        }
    }

}