package com.qwict.studyplanetandroid.data.local.schema

import androidx.room.Entity
import com.qwict.studyplanetandroid.domain.model.Planet

/**
 * Room Entity representing a planet in the local database.
 *
 * @property remoteId The remote identifier of the planet.
 * @property name The name of the planet.
 * @property ownerId The remote identifier of the user to whom the planet belongs.
 */
@Entity(
    tableName = "planets",
    primaryKeys = ["ownerId", "name"],
)
data class PlanetRoomEntity(
    val remoteId: Int = 0,
    val name: String,
    val ownerId: Int,
)

/**
 * Populates a list of default [PlanetRoomEntity] objects.
 *
 * @return A list of default planets.
 */
fun populatePlanets(): List<PlanetRoomEntity> {
    return listOf(
        PlanetRoomEntity(
            name = "Mercury",
            ownerId = 0,
        ),
        PlanetRoomEntity(
            name = "Venus",
            ownerId = 0,
        ),
        PlanetRoomEntity(
            name = "Earth",
            ownerId = 0,
        ),
        PlanetRoomEntity(
            name = "Mars",
            ownerId = 0,
        ),
        PlanetRoomEntity(
            name = "Jupiter",
            ownerId = 0,
        ),
        PlanetRoomEntity(
            name = "Europe",
            ownerId = 0,
        ),
        PlanetRoomEntity(
            name = "Saturn",
            ownerId = 0,
        ),
        PlanetRoomEntity(
            name = "Uranus",
            ownerId = 0,
        ),
        PlanetRoomEntity(
            name = "Neptune",
            ownerId = 0,
        ),
    )
}

/**
 * Converts a [PlanetRoomEntity] into a [Planet] domain model.
 *
 * @return A [Planet] instance.
 */
fun PlanetRoomEntity.asDomainModel() =
    Planet(
        id = remoteId,
        name = name,
    )
