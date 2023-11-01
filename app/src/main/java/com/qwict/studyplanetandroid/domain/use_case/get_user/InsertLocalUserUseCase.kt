package com.qwict.studyplanetandroid.domain.use_case.get_user

import com.qwict.studyplanetandroid.common.AuthenticationSingleton
import com.qwict.studyplanetandroid.common.saveEncryptedPreference
import com.qwict.studyplanetandroid.data.local.AppContainer
import com.qwict.studyplanetandroid.data.local.toUser
import com.qwict.studyplanetandroid.data.remote.dto.AuthenticatedUserDto
import com.qwict.studyplanetandroid.data.remote.dto.toDatabaseUserWithPlanets
import com.qwict.studyplanetandroid.domain.model.User

class InsertLocalUserUseCase(
    private val container: AppContainer,
) {
    suspend operator fun invoke(authenticatedUser: AuthenticatedUserDto): User {
        // Singleton Part
        AuthenticationSingleton.isUserAuthenticated = true
        AuthenticationSingleton.token = authenticatedUser.token

        // Shared Preferences Part
        saveEncryptedPreference("token", authenticatedUser.token)

        // Database Part
        var databaseUserWithPlanets = authenticatedUser.toDatabaseUserWithPlanets()
        container.usersRepository.insert(databaseUserWithPlanets.user)
        container.planetsRepository.insertAll(databaseUserWithPlanets.planets)

        return databaseUserWithPlanets.toUser()
    }
}
