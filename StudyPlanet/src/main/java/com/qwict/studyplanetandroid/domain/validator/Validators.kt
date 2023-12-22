package com.qwict.studyplanetandroid.domain.validator

/**
 * A utility class that provides instances of various validators for common authentication-related fields.
 * Validators include email validation, password validation, username validation, and non-empty text validation.
 *
 * @property emailValidator Validator for email addresses.
 * @property passwordValidator Validator for passwords.
 * @property usernameValidator Validator for usernames.
 * @property notEmptyTextValidator Validator for ensuring text is not empty.
 */
class Validators {
    val emailValidator = ValidateEmail()
    val passwordValidator = ValidatePassword()
    val usernameValidator = ValidateUsername()
    val notEmptyTextValidator = ValidateNotEmptyText()
}
