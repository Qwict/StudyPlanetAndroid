package com.qwict.studyplanetandroid.data.remote.dto

data class HealthDto(
    val env: String,
    val name: String,
    val version: String,
    val androidVersion: String,
    val iosVersion: String,
)

// fun HealthDto.asDomainModel() =
//    Health(
//        version = version,
//        androidVersion = androidVersion,
//    )
