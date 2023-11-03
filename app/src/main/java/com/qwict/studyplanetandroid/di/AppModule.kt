package com.qwict.studyplanetandroid.di

import com.qwict.studyplanetandroid.StudyPlanetApplication
import com.qwict.studyplanetandroid.common.Constants.BASE_URL
import com.qwict.studyplanetandroid.data.local.AppDataContainer
import com.qwict.studyplanetandroid.data.local.OfflinePlanetsRepository
import com.qwict.studyplanetandroid.data.local.OfflineUsersRepository
import com.qwict.studyplanetandroid.data.remote.StudyPlanetApi
import com.qwict.studyplanetandroid.data.repository.StudyPlanetRepositoryImpl
import com.qwict.studyplanetandroid.domain.repository.StudyPlanetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStudyPlanetApi(): StudyPlanetApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StudyPlanetApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStudyPlanetRepository(
        api: StudyPlanetApi,
        planetsRepo: OfflinePlanetsRepository,
        usersRepository: OfflineUsersRepository,
    ): StudyPlanetRepository {
        return StudyPlanetRepositoryImpl(api, planetsRepo, usersRepository)
    }

    @Provides
    @Singleton
    fun provideOfflinePlanetsRepository(): OfflinePlanetsRepository {
        return AppDataContainer(
            StudyPlanetApplication.appContext,
            CoroutineScope(SupervisorJob() + Dispatchers.Main),
        ).planetsRepository
    }

    @Provides
    @Singleton
    fun provideOfflineUsersRepository(): OfflineUsersRepository {
        return AppDataContainer(
            StudyPlanetApplication.appContext,
            CoroutineScope(SupervisorJob() + Dispatchers.Main),
        ).usersRepository
    }

    @Provides
    @Singleton
    fun provideAppContainer(): AppDataContainer {
        return AppDataContainer(
            StudyPlanetApplication.appContext,
            CoroutineScope(SupervisorJob() + Dispatchers.Main),
        )
    }
}
