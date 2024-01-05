package com.qwict.studyplanetandroid.data.remote.dto

import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity

/**
 * Data transfer object (DTO) representing a planet received from the remote API.
 *
 * @property id The unique identifier of the planet.
 * @property name The name of the planet.
 */
data class PlanetDto(
    val id: Int,
    val name: String,
)

/**
 * Converts the [PlanetDto] to a [PlanetRoomEntity] for local database storage.
 *
 * @param remoteId The unique identifier of the user associated with the planet.
 * @return The [PlanetRoomEntity] representation of the planet.
 */
fun PlanetDto.asDatabaseModel(remoteId: Int): PlanetRoomEntity =
    PlanetRoomEntity(
        remoteId = id,
        name = name,
        ownerId = remoteId,
    )
