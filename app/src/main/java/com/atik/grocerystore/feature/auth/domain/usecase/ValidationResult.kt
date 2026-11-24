package com.atik.grocerystore.feature.auth.domain.usecase

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)