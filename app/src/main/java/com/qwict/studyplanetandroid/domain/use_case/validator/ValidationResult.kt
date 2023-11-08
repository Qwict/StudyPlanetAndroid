package com.qwict.studyplanetandroid.domain.use_case.validator

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String = "",
)
