package com.atik.grocerystore.feature.auth.domain.models

data class AuthSession(val user: User, val token: String)