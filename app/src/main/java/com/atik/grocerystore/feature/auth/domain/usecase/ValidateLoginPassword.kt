package com.atik.grocerystore.feature.auth.domain.usecase

class ValidateLoginPassword {

    fun execute(password: String): ValidationResult {

        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password can't be blank"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}