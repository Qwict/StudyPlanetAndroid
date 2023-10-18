package com.qwict.studyplanetandroid.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class HealthDto(
    var env: String = "",
    var version: String = "",
    var name: String = "",
)