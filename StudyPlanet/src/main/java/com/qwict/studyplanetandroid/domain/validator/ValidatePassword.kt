package com.qwict.studyplanetandroid.domain.validator
/**
 * Validator for ensuring the validity of a password, including matching with a confirmation password.
 */
class ValidatePassword {
    /**
     * Validates the provided password and confirmation password based on specific criteria.
     *
     * @param password The password to be validated.
     * @param confirmPassword The confirmation password to be compared with the original password.
     * @return A [ValidationResult] indicating the result of the validation.
     */
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
