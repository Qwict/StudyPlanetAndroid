package com.qwict.studyplanetandroid.data.remote.dto

import com.qwict.studyplanetandroid.domain.model.Health
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HealthDto(
    val env: String,
    val name: String,
    val version: String,
)

fun HealthDto.asDomainModel() = Health(
    version = version,
)
