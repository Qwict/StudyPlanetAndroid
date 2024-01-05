package com.qwict.studyplanetandroid.common

data class DecodedPayload(
    val remoteId: Int,
    val email: String,
    val iat: Int,
    val exp: Int,
)

// data class DecodedHeader(
//    val alg: String,
//    val typ: String,
// )
