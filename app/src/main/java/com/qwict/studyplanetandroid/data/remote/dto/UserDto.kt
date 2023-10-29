package com.qwict.studyplanetandroid.data.remote.dto

data class UserDto(
    val discoveredPlanets: List<DiscoveredPlanetDto>,
    val email: String,
    val experience: Int,
    val id: Int,
    val name: String,
)
