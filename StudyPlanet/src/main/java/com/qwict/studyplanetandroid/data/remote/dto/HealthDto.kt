package com.qwict.studyplanetandroid.data.remote.dto

import com.qwict.studyplanetandroid.domain.model.Health

data class HealthDto(
    val env: String,
    val name: String,
    val version: String,
    val androidVersion: String,
    val iosVersion: String,
)

fun HealthDto.asDomainModel() = Health(
    version = version,
    androidVersion = androidVersion,
)
