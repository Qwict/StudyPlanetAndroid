package com.qwict.studyplanetandroid.data.remote.dto

import com.qwict.studyplanetandroid.data.local.schema.UserRoomEntity
import com.squareup.moshi.JsonClass

/**
 * Data transfer object (DTO) representing a user received from the remote API.
 *
 * @property id The unique identifier of the user.
 * @property email The email address of the user.
 * @property name The name of the user.
 * @property experience The experience level of the user.
 * @property discoveredPlanets The list of planets discovered by the user.
 */
@JsonClass(generateAdapter = true)
data class UserDto(
    val id: Int,
    val email: String,
    val name: String,
    val experience: Int,
    val discoveredPlanets: List<PlanetDto>,
)

/**
 * Converts the [UserDto] to a [UserRoomEntity] for local database storage.
 *
 * @return The [UserRoomEntity] representation of the user.
 */
fun UserDto.asDatabaseModel(): UserRoomEntity {
    return UserRoomEntity(
        remoteId = id,
        name = name,
        email = email,
        experience = experience,
    )
}
