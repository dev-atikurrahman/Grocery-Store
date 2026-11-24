package com.atik.grocerystore.feature.auth.data.preferences

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    val isLoggedIn: Flow<Boolean>
    suspend fun setLoggedIn(loggedIn: Boolean)
    val authToken: Flow<String?>
    suspend fun saveSession(token: String, rememberMe: Boolean = true)
    suspend fun clearSession()
    suspend fun clearSessionIfNotRemembered()

}