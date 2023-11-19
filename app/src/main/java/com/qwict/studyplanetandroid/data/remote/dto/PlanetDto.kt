package com.qwict.studyplanetandroid.data.remote.dto

import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity
import com.qwict.studyplanetandroid.domain.model.Planet
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlanetDto(
    val id: Int,
    val name: String,
)

fun PlanetDto.asDatabaseModel(userId: Int): PlanetRoomEntity = PlanetRoomEntity(
    remoteId = id,
    name = name,
    userOwnerId = userId,
)

fun PlanetDto.asDomainModel() = Planet(
    id = id,
    name = name,
)
