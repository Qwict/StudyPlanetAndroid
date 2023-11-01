package com.qwict.studyplanetandroid.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthenticationDto(
    var token: String? = null,
)
