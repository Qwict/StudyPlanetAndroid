package com.qwict.studyplanetandroid.domain.validator

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String = "",
)
