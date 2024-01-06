package com.qwict.studyplanetandroid.data.remote.dto

import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity
import com.qwict.studyplanetandroid.data.local.schema.asDomainModel
import com.qwict.studyplanetandroid.domain.model.ActionResponse

data class DiscoverActionResponseDto(
    val hasFoundNewPlanet: Boolean,
    val planet: PlanetDto?,
    val experience: Int,
)

fun DiscoverActionResponseDto.asDomainModel(planet: PlanetRoomEntity?) =
    ActionResponse(
        hasFoundNewPlanet = hasFoundNewPlanet,
        planet = planet?.asDomainModel(),
        gainedExperience = experience,
    )
