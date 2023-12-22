package com.qwict.studyplanetandroid.domain.validator

/**
 * Validator for ensuring the validity of a username.
 */
class ValidateUsername {
    /**
     * Validates the provided username based on specific criteria.
     *
     * @param username The username to be validated.
     * @return A [ValidationResult] indicating the result of the validation.
     */
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
