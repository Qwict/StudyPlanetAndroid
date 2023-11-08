package com.qwict.studyplanetandroid.data.local.schema

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.qwict.studyplanetandroid.common.Constants.DEFAULT_UUID
import com.qwict.studyplanetandroid.domain.model.Planet
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import java.util.UUID

@Entity(
    tableName = "planets",
    indices = [
        Index(
            value = arrayOf("name"),
            unique = true,
        ),
    ],
)
data class PlanetRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val remoteId: Int = 0,
    val name: String,
    val userOwnerId: Int,
    val userUuid: UUID,
)

fun populatePlanets(): List<PlanetRoomEntity> {
    return listOf(
        PlanetRoomEntity(
            name = "Mercury",
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        PlanetRoomEntity(
            name = "Venus",
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        PlanetRoomEntity(
            name = "Earth",
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        PlanetRoomEntity(
            name = "Mars",
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        PlanetRoomEntity(
            name = "Jupiter",
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        PlanetRoomEntity(
            name = "Europe",
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        PlanetRoomEntity(
            name = "Saturn",
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        PlanetRoomEntity(
            name = "Uranus",
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        PlanetRoomEntity(
            name = "Neptune",
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
    )
}

fun toPlanet(jsonPlanet: JsonObject, userId: Int): PlanetRoomEntity {
    return PlanetRoomEntity(
        id = jsonPlanet.jsonObject["id"].toString().toInt(),
        name = jsonPlanet.jsonObject["name"].toString(),
        userOwnerId = userId,
        userUuid = DEFAULT_UUID,
    )
}

fun PlanetRoomEntity.toPlanet() = Planet(
    id = id,
    name = name,
)
