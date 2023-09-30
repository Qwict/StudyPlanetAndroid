package com.qwict.studyplanetandroid.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorResponse(
    @JsonProperty("message") val message: String

)
