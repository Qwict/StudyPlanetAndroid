package com.qwict.studyplanetandroid.domain.validator

/**
 * Validator for ensuring the validity of a password, including matching with a confirmation password.
 */
class ValidatePassword {
    /**
     * Validates a new password and its confirmation.
     *
     * @param password The new password to be validated.
     * @param confirmPassword The confirmation of the new password.
     * @return A [ValidationResult] object indicating the success or failure of the validation.
     */
    operator fun invoke(
        password: String,
        confirmPassword: String,
    ): ValidationResult {
        var errorMessages = emptyList<String>()
        if (password != confirmPassword) {
            errorMessages = errorMessages + "The passwords do not match."
        } else {
            if (password.length < 8) {
                errorMessages = addFirstPartIfNeeded(errorMessages)
                errorMessages = errorMessages + "8 characters"
            }
            if (!password.contains(Regex("[0-9]"))) {
                errorMessages = addFirstPartIfNeeded(errorMessages)
                errorMessages = errorMessages + "one digit"
            }
            if (!password.contains(Regex("[a-z]"))) {
                errorMessages = addFirstPartIfNeeded(errorMessages)
                errorMessages = errorMessages + "one lowercase letter"
            }
        }

        return ValidationResult(
            successful = errorMessages.isEmpty(),
            errorMessage = errorMessages.joinToString(separator = " "),
        )
    }

    /**
     * Adds the first part of the error message if needed.
     *
     * @param errorMessages The list of error messages.
     * @return The updated list of error messages.
     */
    private fun addFirstPartIfNeeded(errorMessages: List<String>): List<String> {
        if (errorMessages.isEmpty()) {
            return errorMessages + "The password must at least contain:"
        }
        return errorMessages
    }
}
