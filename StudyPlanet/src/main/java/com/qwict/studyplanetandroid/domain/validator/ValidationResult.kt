package com.qwict.studyplanetandroid.domain.validator
// Based on the great tutorial from Philipp Lackner

/**
 * A data class representing the result of a validation process.
 *
 * @property successful Indicates whether the validation was successful.
 * @property errorMessage A descriptive error message in case the validation was not successful.
 */
data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String = "",
)
