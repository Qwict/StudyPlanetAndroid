package com.qwict.studyplanetandroid.data.remote.dto

import java.util.UUID

data class RegisterDto(
    val userUuid: UUID,
    val name: String,
    val email: String,
    val password: String,
)
