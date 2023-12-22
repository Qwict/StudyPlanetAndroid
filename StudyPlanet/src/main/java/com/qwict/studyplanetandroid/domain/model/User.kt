package com.qwict.studyplanetandroid.domain.model

data class User(
    val discoveredPlanets: List<Planet>,
    val email: String,
    val experience: Int,
    val id: Int,
    val name: String,
)
