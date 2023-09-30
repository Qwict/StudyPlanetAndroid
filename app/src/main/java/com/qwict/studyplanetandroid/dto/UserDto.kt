package com.qwict.studyplanetandroid.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UserDto(
    @JsonProperty("id") val id: Long,
    @JsonProperty("name") val name: String,
    @JsonProperty("studyHours") val studyScore: Double
)