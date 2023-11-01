package com.qwict.studyplanetandroid.data.local

import android.content.Context
import com.qwict.studyplanetandroid.data.repository.StudyPlanetDatabase
import kotlinx.coroutines.CoroutineScope

interface AppContainer {
    val planetsRepository: OfflinePlanetsRepository
    val usersRepository: OfflineUsersRepository
}
class AppDataContainer(private val context: Context, private val scope: CoroutineScope) :
    AppContainer {
    override val planetsRepository: OfflinePlanetsRepository by lazy {
        OfflinePlanetsRepositoryImpl(StudyPlanetDatabase.getDatabase(context, scope).planetDao())
    }
    override val usersRepository: OfflineUsersRepository by lazy {
        OfflineUsersRepositoryImpl(StudyPlanetDatabase.getDatabase(context, scope).userDao())
    }
}
