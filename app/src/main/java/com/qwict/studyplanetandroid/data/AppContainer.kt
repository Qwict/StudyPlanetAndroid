package com.qwict.studyplanetandroid.data

import android.content.Context
import kotlinx.coroutines.CoroutineScope

interface AppContainer {
    val planetRepository: PlanetsRepository
}
class AppDataContainer(private val context: Context, private val scope: CoroutineScope) : AppContainer {
    override val planetRepository: PlanetsRepository by lazy {
        OfflinePlanetRepository(StudyPlanetDatabase.getDatabase(context, scope).planetDao())
    }
}
