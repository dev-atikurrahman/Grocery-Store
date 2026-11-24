package com.atik.grocerystore.feature.auth.data.models

import kotlinx.serialization.Serializable


@Serializable
data class LoginRequestDto(
    val email: String,
    val password: String
)

@Serializable
data class UserDto(
    val id: Int,
    val email: String,
    val role: String = "super_admin" // Roles can be 'super_admin' or 'admin'; a 'super_admin' has access to everything, while an 'admin' has access to specific areas.
    )