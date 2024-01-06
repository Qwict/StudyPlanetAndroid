package com.qwict.studyplanetandroid.domain.model

data class ActionResponse(
    val hasFoundNewPlanet: Boolean,
    val planet: Planet?,
    val gainedExperience: Int,
)
