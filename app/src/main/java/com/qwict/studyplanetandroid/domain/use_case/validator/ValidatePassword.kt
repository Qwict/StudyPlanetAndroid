package com.qwict.studyplanetandroid.domain.use_case.validator

class ValidatePassword {
    operator fun invoke(password: String, confirmPassword: String): ValidationResult {
        return if (password != confirmPassword) {
            ValidationResult(
                successful = false,
                errorMessage = "The passwords do not match",
            )
        } else if (password.length < 8) {
            ValidationResult(
                successful = false,
                errorMessage = "The password must be at least 8 characters long",
            )
        } else if (!password.contains(Regex("[0-9]"))) {
            ValidationResult(
                successful = false,
                errorMessage = "The password must contain at least one digit",
            )
        } else if (!password.contains(Regex("[a-z]"))) {
            ValidationResult(
                successful = false,
                errorMessage = "The password must contain at least one lowercase letter",
            )
        } else {
            ValidationResult(
                successful = true,
            )
        }
    }
}
