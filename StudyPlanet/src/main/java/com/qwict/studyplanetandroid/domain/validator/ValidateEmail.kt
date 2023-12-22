package com.qwict.studyplanetandroid.domain.validator

import android.util.Patterns

/**
 * Validator for email addresses.
 */
class ValidateEmail {
    /**
     * Validates the provided email address.
     *
     * @param email The email address to be validated.
     * @return A [ValidationResult] indicating the result of the email validation.
     */
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
