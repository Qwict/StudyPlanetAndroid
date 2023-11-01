package com.qwict.studyplanetandroid.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.common.Constants.DEFAULT_UUID
import com.qwict.studyplanetandroid.domain.model.Planet
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import java.util.UUID

@Entity(tableName = "planets")
data class DatabasePlanet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val remoteId: Int = 0,
    val name: String = "Unknown Planet",
    val userOwnerId: Int,
    val userUuid: UUID,
    val imageId: Int = R.drawable.earth,
)

fun populatePlanets(): List<DatabasePlanet> {
    return listOf(
        DatabasePlanet(
            name = "Mercury",
            imageId = R.drawable.mercury,
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        DatabasePlanet(
            name = "Venus",
            imageId = R.drawable.venus,
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        DatabasePlanet(
            name = "Earth",
            imageId = R.drawable.earth,
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        DatabasePlanet(
            name = "Mars",
            imageId = R.drawable.mars,
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        DatabasePlanet(
            name = "Jupiter",
            imageId = R.drawable.jupiter,
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        DatabasePlanet(
            name = "Europe",
            imageId = R.drawable.europa,
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        DatabasePlanet(
            name = "Saturn",
            imageId = R.drawable.saturn,
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        DatabasePlanet(
            name = "Uranus",
            imageId = R.drawable.uranus,
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
        DatabasePlanet(
            name = "Neptune",
            imageId = R.drawable.neptune,
            userOwnerId = 0,
            userUuid = DEFAULT_UUID,
        ),
    )
}

fun toPlanet(jsonPlanet: JsonObject, userId: Int): DatabasePlanet {
    return DatabasePlanet(
        id = jsonPlanet.jsonObject["id"].toString().toInt(),
        name = jsonPlanet.jsonObject["name"].toString(),
        imageId = jsonPlanet.jsonObject["image"].toString().toInt(),
        userOwnerId = userId,
        userUuid = DEFAULT_UUID,
    )
}

fun DatabasePlanet.toPlanet(user: DatabaseUser) = Planet(
    userUuid = user.userUuid,
    userOwnerId = user.id,
    id = id,
    name = name,
    imageId = imageId,
)
