package com.qwict.studyplanetandroid.data.remote.dto

import com.qwict.studyplanetandroid.domain.model.User

data class AuthenticatedUserDto(
    val token: String,
    val user: UserDto,
    val validated: Boolean,
)

fun AuthenticatedUserDto.toUser() = User(
    discoveredPlanets = user.discoveredPlanets,
    email = user.email,
    experience = user.experience,
    id = user.id,
    name = user.name,
)
