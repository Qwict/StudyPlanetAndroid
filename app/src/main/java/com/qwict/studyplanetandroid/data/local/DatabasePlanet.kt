package com.qwict.studyplanetandroid.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.qwict.studyplanetandroid.R
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
data class DatabasePlanet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val remoteId: Int = 0,
    val name: String,
    val userOwnerId: Int,
    val userUuid: UUID,
)

fun populatePlanets(): List<DatabasePlanet> {
    return listOf(
        DatabasePlanet(
            name = "Mercury",
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        DatabasePlanet(
            name = "Venus",
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        DatabasePlanet(
            name = "Earth",
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        DatabasePlanet(
            name = "Mars",
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        DatabasePlanet(
            name = "Jupiter",
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        DatabasePlanet(
            name = "Europe",
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        DatabasePlanet(
            name = "Saturn",
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        DatabasePlanet(
            name = "Uranus",
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        DatabasePlanet(
            name = "Neptune",
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
    )
}

fun toPlanet(jsonPlanet: JsonObject, userId: Int): DatabasePlanet {
    return DatabasePlanet(
        id = jsonPlanet.jsonObject["id"].toString().toInt(),
        name = jsonPlanet.jsonObject["name"].toString(),
        userOwnerId = userId,
        userUuid = DEFAULT_UUID,
    )
}

fun DatabasePlanet.toPlanet() = Planet(
    id = id,
    name = name,
)
