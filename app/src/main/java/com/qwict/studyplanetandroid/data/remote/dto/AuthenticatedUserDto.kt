package com.qwict.studyplanetandroid.data.remote.dto

import com.qwict.studyplanetandroid.data.local.DatabaseUser
import com.qwict.studyplanetandroid.data.local.DatabaseUserWithPlanets
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthenticatedUserDto(
    val token: String,
    val user: UserDto,
    val validated: Boolean,
)

// TODO: Ask: Should not exist because Flow is always Api -> Database -> UI (so Api -> UI is not possible)
// fun AuthenticatedUserDto.toUser() = User(
//    discoveredPlanets = user.discoveredPlanets,
//    email = user.email,
//    experience = user.experience,
//    id = user.id,
//    name = user.name,
// )

// Might be useful when a user registers... (there are no planets yet...)
fun AuthenticatedUserDto.toDatabaseUser() = DatabaseUser(
    email = user.email,
    name = user.name,
    userUuid = user.userUuid,
    experience = user.experience,
    remoteId = user.id,
)

fun AuthenticatedUserDto.toDatabaseUserWithPlanets() = DatabaseUserWithPlanets(
    user = user.asDatabaseModel(),
    planets = user.discoveredPlanets.map { it.asDatabaseModel(user.id, user.userUuid) },
)
