package com.qwict.studyplanetandroid.dto

// import com.fasterxml.jackson.annotation.JsonProperty

data class UserDto(
    var id: String = "",
    var name: String = "",
    var email: String = "",
    var experience: Int = 0,
//    @JsonProperty("id") val id: Long,
//    @JsonProperty("name") val name: String,
//    @JsonProperty("email") val email: String,
//    @JsonProperty("experience") val studyScore: Double,
)
