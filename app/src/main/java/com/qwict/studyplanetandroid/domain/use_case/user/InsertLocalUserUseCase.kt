package com.qwict.studyplanetandroid.domain.use_case.user // ktlint-disable package-name

import com.qwict.studyplanetandroid.common.AuthenticationSingleton
import com.qwict.studyplanetandroid.common.saveEncryptedPreference
import com.qwict.studyplanetandroid.data.remote.dto.AuthenticatedUserDto
import com.qwict.studyplanetandroid.data.remote.dto.asDomainModel
import com.qwict.studyplanetandroid.domain.model.User

fun insertLocalUserUseCase(authenticatedUserDto: AuthenticatedUserDto): User {
    // Shared Preferences Part
    saveEncryptedPreference("token", authenticatedUserDto.token)

    // Singleton Part, validation should work because token was saved in shared preferences
    AuthenticationSingleton.validateUser()

    // Convert user to domain model and emit it
    return authenticatedUserDto.asDomainModel()
}
