package com.qwict.studyplanetandroid.data.local

import android.content.Context
import com.qwict.studyplanetandroid.data.local.database.OfflinePlanetsRepository
import com.qwict.studyplanetandroid.data.local.database.OfflinePlanetsRepositoryImpl
import com.qwict.studyplanetandroid.data.local.database.OfflineUsersRepository
import com.qwict.studyplanetandroid.data.local.database.OfflineUsersRepositoryImpl
import kotlinx.coroutines.CoroutineScope

//interface RoomContainer {
//    val planetsRepository: OfflinePlanetsRepository
//    val usersRepository: OfflineUsersRepository
//}
//class AppDataContainer(private val context: Context, private val scope: CoroutineScope) :
//    RoomContainer {
//    override val planetsRepository: OfflinePlanetsRepository by lazy {
//        OfflinePlanetsRepositoryImpl(StudyPlanetDatabase.getDatabase(context, scope).planetDao())
//    }
//    override val usersRepository: OfflineUsersRepository by lazy {
//        OfflineUsersRepositoryImpl(StudyPlanetDatabase.getDatabase(context, scope).userDao())
//    }
//}
