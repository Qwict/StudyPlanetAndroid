package com.qwict.studyplanetandroid.domain.validator

/**
 * Validator for ensuring that a text field is not empty.
 */
class ValidateNotEmptyText {
    /**
     * Validates the provided text to ensure it is not empty.
     *
     * @param text The text to be validated.
     * @param name The name or label associated with the text field for error messages.
     * @return A [ValidationResult] indicating the result of the validation.
     */
    operator fun invoke(text: String, name: String): ValidationResult {
        return if (text.isBlank()) {
            ValidationResult(
                successful = false,
                errorMessage = "$name must be filled in",
            )
        } else {
            ValidationResult(
                successful = true,
            )
        }
    }
}
