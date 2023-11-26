package com.qwict.studyplanetandroid.data.local.schema

import androidx.room.Entity
import com.qwict.studyplanetandroid.domain.model.Planet
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

@Entity(
    tableName = "planets",
    primaryKeys = ["userOwnerId", "name"],
)
data class PlanetRoomEntity(
    val remoteId: Int = 0,
    val name: String,
    val userOwnerId: Int,
)

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

fun toPlanet(jsonPlanet: JsonObject, userId: Int): PlanetRoomEntity {
    return PlanetRoomEntity(
        name = jsonPlanet.jsonObject["name"].toString(),
        userOwnerId = userId,
    )
}

fun PlanetRoomEntity.toPlanet() = Planet(
    id = remoteId,
    name = name,
)
