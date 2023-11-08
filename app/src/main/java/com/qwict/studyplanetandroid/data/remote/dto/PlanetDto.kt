package com.qwict.studyplanetandroid.data.remote.dto

import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity
import com.qwict.studyplanetandroid.domain.model.Planet
import com.squareup.moshi.JsonClass
import java.util.UUID

@JsonClass(generateAdapter = true)
data class PlanetDto(
    val id: Int,
    val name: String,
)

// fun PlanetDto.toDatabasePlanet(userOwnerId: Int, userUuid: UUID) = DatabasePlanet(
//    remoteId = id,
//    name = name,
//    userOwnerId = userOwnerId,
//    userUuid = userUuid,
//    imageId = image,
// )
//
// fun PlanetDto.toPlanet(user: UserDto) = Planet(
//    userUuid = user.userUuid,
//    userOwnerId = user.id,
//    id = id,
//    name = name,
//    imageId = image,
// )

/**
 * Convert Network results to database objects
 */
fun PlanetDto.asDatabaseModel(userId: Int, userUuid: UUID): PlanetRoomEntity = PlanetRoomEntity(
    remoteId = id,
    name = name,
    userOwnerId = userId,
    userUuid = userUuid,
)

fun PlanetDto.asDomainModel() = Planet(
    id = id,
    name = name,
)
