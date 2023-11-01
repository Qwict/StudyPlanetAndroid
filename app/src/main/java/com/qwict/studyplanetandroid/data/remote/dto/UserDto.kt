package com.qwict.studyplanetandroid.data.remote.dto

import com.qwict.studyplanetandroid.data.local.DatabaseUser
import com.squareup.moshi.JsonClass
import java.util.UUID

@JsonClass(generateAdapter = true)
data class UserDto(
    val id: Int,
    val userUuid: UUID,
    val email: String,
    val name: String,
    val experience: Int,
    val discoveredPlanets: List<PlanetDto>,
)

/**
 * Convert Network results to database objects
 */
fun UserDto.asDatabaseModel(): DatabaseUser {
    return DatabaseUser(
        remoteId = id,
        userUuid = userUuid,
        name = name,
        email = email,
        experience = experience,
    )
}
