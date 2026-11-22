package com.atik.grocerystore.feature.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atik.grocerystore.feature.auth.data.preferences.UserPreferences
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val userPreferences: UserPreferences
) : ViewModel() {

    fun completeOnboarding(onCompleted: () -> Unit) {
        viewModelScope.launch {
            userPreferences.setOnboardingCompleted(true)
            onCompleted()
        }
    }
}