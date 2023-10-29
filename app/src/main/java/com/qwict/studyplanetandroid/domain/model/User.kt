package com.qwict.studyplanetandroid.domain.model

import com.qwict.studyplanetandroid.data.remote.dto.DiscoveredPlanetDto

data class User(
    val discoveredPlanets: List<DiscoveredPlanetDto>,
    val email: String,
    val experience: Int,
    val id: Int,
    val name: String,
)
