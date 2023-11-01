package com.qwict.studyplanetandroid.domain.model

import com.qwict.studyplanetandroid.data.local.DatabaseUserWithPlanets

data class User(
    val discoveredPlanets: List<Planet>,
    val email: String,
    val experience: Int,
    val id: Int,
    val name: String,
)