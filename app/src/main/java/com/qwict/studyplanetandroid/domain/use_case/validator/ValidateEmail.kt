package com.qwict.studyplanetandroid.domain.use_case.validator

import android.util.Patterns

class ValidateEmail {
    operator fun invoke(email: String): ValidationResult {
        return if (email.isEmpty()) {
            ValidationResult(
                successful = false,
                errorMessage = "Email cannot be empty",
            )
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ValidationResult(
                successful = false,
                errorMessage = "Email is not valid",
            )
        } else {
            ValidationResult(
                successful = true,
            )
        }
    }
}
