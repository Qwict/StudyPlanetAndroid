package com.qwict.studyplanetandroid.domain.model

data class User(
    val discoveredPlanets: List<Planet>,
    val email: String,
    val name: String,
    val experience: Int,
    val currentLevel: Int,
    val currentLevelProgress: Int,
    val experienceForCurrentLevel: Int,
    val experienceForNextLevel: Int,
    val experienceProgress: Float,
)
