package com.qwict.studyplanetandroid.data.remote.dto

import com.qwict.studyplanetandroid.data.local.schema.DatabaseUserWithPlanets
import com.qwict.studyplanetandroid.data.local.schema.UserRoomEntity

/**
 * Data transfer object (DTO) representing an authenticated user received from the remote API.
 *
 * @property token The authentication token associated with the user.
 * @property user The detailed information about the authenticated user.
 * @property validated Indicates whether the user has been validated.
 */
data class AuthenticatedUserDto(
    val token: String,
    val user: UserDto,
    val validated: Boolean,
)

/**
 * Converts the [AuthenticatedUserDto] to a [UserRoomEntity] for local database storage.
 * This function might be useful when a user registers with no discovered planets yet.
 *
 * @return The [UserRoomEntity] representation of the authenticated user.
 */
fun AuthenticatedUserDto.asDatabaseModel() = UserRoomEntity(
    email = user.email,
    name = user.name,
    experience = user.experience,
    remoteId = user.id,
)

/**
 * Converts the [AuthenticatedUserDto] to a [DatabaseUserWithPlanets] for local database storage.
 *
 * @return The [DatabaseUserWithPlanets] representation of the authenticated user with discovered planets.
 */
fun AuthenticatedUserDto.asDatabaseEntityWithPlanets() = DatabaseUserWithPlanets(
    user = user.asDatabaseModel(),
    planets = user.discoveredPlanets.map { it.asDatabaseModel(user.id) },
)
