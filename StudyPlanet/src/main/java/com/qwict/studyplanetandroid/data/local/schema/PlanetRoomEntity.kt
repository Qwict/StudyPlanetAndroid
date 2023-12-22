package com.qwict.studyplanetandroid.data.local.schema

import androidx.room.Entity
import com.qwict.studyplanetandroid.domain.model.Planet

/**
 * Room Entity representing a planet in the local database.
 *
 * @property remoteId The remote identifier of the planet.
 * @property name The name of the planet.
 * @property userOwnerId The remote identifier of the user to whom the planet belongs.
 */
@Entity(
    tableName = "planets",
    primaryKeys = ["userOwnerId", "name"],
)
data class PlanetRoomEntity(
    val remoteId: Int = 0,
    val name: String,
    val userOwnerId: Int,
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
            userOwnerId = 0,
        ),
        PlanetRoomEntity(
            name = "Venus",
            userOwnerId = 0,
        ),
        PlanetRoomEntity(
            name = "Earth",
            userOwnerId = 0,
        ),
        PlanetRoomEntity(
            name = "Mars",
            userOwnerId = 0,
        ),
        PlanetRoomEntity(
            name = "Jupiter",
            userOwnerId = 0,
        ),
        PlanetRoomEntity(
            name = "Europe",
            userOwnerId = 0,
        ),
        PlanetRoomEntity(
            name = "Saturn",
            userOwnerId = 0,
        ),
        PlanetRoomEntity(
            name = "Uranus",
            userOwnerId = 0,
        ),
        PlanetRoomEntity(
            name = "Neptune",
            userOwnerId = 0,
        ),
    )
}

/**
 * Converts a [PlanetRoomEntity] into a [Planet] domain model.
 *
 * @return A [Planet] instance.
 */
fun PlanetRoomEntity.toPlanet() = Planet(
    id = remoteId,
    name = name,
)
