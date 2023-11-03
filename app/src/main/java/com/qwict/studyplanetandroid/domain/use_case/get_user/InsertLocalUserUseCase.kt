package com.qwict.studyplanetandroid.domain.use_case.get_user

import android.util.Log
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
        Log.d(
            "InsertLocalUserUseCase",
            " token : ${authenticatedUser.token},\n" +
                " validated : ${authenticatedUser.validated}, \n" +
                "userId : ${authenticatedUser.user.name}, \n" +
                "email : ${authenticatedUser.user.email}",
        )

        // Singleton Part
        AuthenticationSingleton.isUserAuthenticated = true
        AuthenticationSingleton.token = authenticatedUser.token

        // Shared Preferences Part
        saveEncryptedPreference("token", authenticatedUser.token)

        // Database Part
        var databaseUserWithPlanets = authenticatedUser.toDatabaseUserWithPlanets()
        container.usersRepository.insert(databaseUserWithPlanets.user)
        container.planetsRepository.insertAll(databaseUserWithPlanets.planets)

        var user = databaseUserWithPlanets.toUser()
        Log.d("InsertLocalUserUseCase", "user: ${user.email}")
        return user
    }
}
