package com.qwict.studyplanetandroid.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class HealthDto(
    @JsonProperty("env") val env: String,
    @JsonProperty("version") val version: String,
    @JsonProperty("name") val name: String,
)