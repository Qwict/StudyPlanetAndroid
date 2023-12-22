package com.qwict.studyplanetandroid.data.remote.dto

import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity
import com.qwict.studyplanetandroid.domain.model.Planet
import com.squareup.moshi.JsonClass

/**
 * Data transfer object (DTO) representing a planet received from the remote API.
 *
 * @property id The unique identifier of the planet.
 * @property name The name of the planet.
 */
@JsonClass(generateAdapter = true)
data class PlanetDto(
    val id: Int,
    val name: String,
)

/**
 * Converts the [PlanetDto] to a [PlanetRoomEntity] for local database storage.
 *
 * @param userId The unique identifier of the user associated with the planet.
 * @return The [PlanetRoomEntity] representation of the planet.
 */
fun PlanetDto.asDatabaseModel(userId: Int): PlanetRoomEntity = PlanetRoomEntity(
    remoteId = id,
    name = name,
    userOwnerId = userId,
)

/**
 * Converts the [PlanetDto] to a [Planet] domain model for application use.
 *
 * @return The [Planet] representation of the planet.
 */
fun PlanetDto.asDomainModel() = Planet(
    id = id,
    name = name,
)
