package com.qwict.studyplanetandroid.data.remote.dto

import com.qwict.studyplanetandroid.data.local.schema.UserRoomEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    val id: Int,
    val email: String,
    val name: String,
    val experience: Int,
    val discoveredPlanets: List<PlanetDto>,
)

/**
 * Convert Network results to database objects
 */
fun UserDto.asDatabaseModel(): UserRoomEntity {
    return UserRoomEntity(
        remoteId = id,
        name = name,
        email = email,
        experience = experience,
    )
}
