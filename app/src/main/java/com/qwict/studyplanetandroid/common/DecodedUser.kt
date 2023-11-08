package com.qwict.studyplanetandroid.common

import java.util.UUID

data class DecodedUser(
    val id: Int,
    val uuid: UUID,
    val name: String,
    val email: String,
)
