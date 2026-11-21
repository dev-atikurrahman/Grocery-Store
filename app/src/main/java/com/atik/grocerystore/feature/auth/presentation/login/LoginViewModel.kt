package com.atik.grocerystore.feature.auth.presentation.login

import androidx.lifecycle.ViewModel
import com.atik.grocerystore.feature.auth.data.preferences.UserPreferences

class LoginViewModel(
    private val userPreferences: UserPreferences,

    ) : ViewModel() {
    companion object {
        const val AUTH_TAG = "AUTH_DEBUG"
    }

}