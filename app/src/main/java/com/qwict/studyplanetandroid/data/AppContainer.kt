package com.qwict.studyplanetandroid.data

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope

interface AppContainer {
    val planetRepository: PlanetsRepository
}
class AppDataContainer(private val context: Context, private val scope: CoroutineScope) : AppContainer {
    override val planetRepository: PlanetsRepository by lazy {
        Log.i("AppDataContainer", "planetRepository created")
        OfflinePlanetRepository(StudyPlanetDatabase.getDatabase(context, scope).planetDao())
    }
}
