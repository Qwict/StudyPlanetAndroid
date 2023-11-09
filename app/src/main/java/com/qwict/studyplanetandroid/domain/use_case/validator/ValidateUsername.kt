package com.qwict.studyplanetandroid.domain.use_case.validator

class ValidateUsername {
    operator fun invoke(username: String): ValidationResult {
        return if (username.isEmpty()) {
            ValidationResult(
                successful = false,
                errorMessage = "Username cannot be empty",
            )
        } else {
            ValidationResult(
                successful = true,
            )
        }
    }
}
