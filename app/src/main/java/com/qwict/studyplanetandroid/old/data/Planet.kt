package com.qwict.studyplanetandroid.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.qwict.studyplanetandroid.R
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

@Entity(tableName = "planets")
data class Planet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val remoteId: Int = 0,
    val name: String = "Unknown Planet",
    val userOwnerId: Int,
    val imageId: Int = R.drawable.earth,
)

fun toPlanet(jsonPlanet: JsonObject, userId: Int): Planet {
    return Planet(
        id = jsonPlanet.jsonObject["id"].toString().toInt(),
        name = jsonPlanet.jsonObject["name"].toString(),
        imageId = jsonPlanet.jsonObject["image"].toString().toInt(),
        userOwnerId = userId,
    )
}
