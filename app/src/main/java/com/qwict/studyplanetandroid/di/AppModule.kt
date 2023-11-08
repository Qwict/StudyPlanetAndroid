package com.qwict.studyplanetandroid.di

import com.qwict.studyplanetandroid.StudyPlanetApplication
import com.qwict.studyplanetandroid.common.Constants.BASE_URL
import com.qwict.studyplanetandroid.data.local.StudyPlanetDatabase
import com.qwict.studyplanetandroid.data.local.database.OfflinePlanetsRepository
import com.qwict.studyplanetandroid.data.local.database.OfflinePlanetsRepositoryImpl
import com.qwict.studyplanetandroid.data.local.database.OfflineUsersRepository
import com.qwict.studyplanetandroid.data.local.database.OfflineUsersRepositoryImpl
import com.qwict.studyplanetandroid.data.remote.StudyPlanetApi
import com.qwict.studyplanetandroid.data.repository.StudyPlanetRepository
import com.qwict.studyplanetandroid.data.repository.StudyPlanetRepositoryImpl
import com.qwict.studyplanetandroid.domain.use_case.planets.GetLocalPlanetsUseCase
import com.qwict.studyplanetandroid.domain.use_case.planets.GetOnlinePlanetsUseCase
import com.qwict.studyplanetandroid.domain.use_case.user.AuthenticateUseCase
import com.qwict.studyplanetandroid.domain.use_case.user.LoginUseCase
import com.qwict.studyplanetandroid.domain.use_case.user.RegisterUseCase
import com.qwict.studyplanetandroid.domain.use_case.validator.Validators
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

    // Provides the API (remote server) to application
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
        return StudyPlanetRepositoryImpl(api, usersRepository, planetsRepo)
    }

    // Provides the part of database to application (planets)
    @Provides
    @Singleton
    fun providePlanetDatabase(): OfflinePlanetsRepository {
        return OfflinePlanetsRepositoryImpl(
            StudyPlanetDatabase.getDatabase(
                StudyPlanetApplication.appContext,
                CoroutineScope(SupervisorJob() + Dispatchers.Main),
            ).planetDao(),
        )
    }

    // Provides the part of database to application (users)
    @Provides
    @Singleton
    fun provideUserDatabase(): OfflineUsersRepository {
        return OfflineUsersRepositoryImpl(
            StudyPlanetDatabase.getDatabase(
                StudyPlanetApplication.appContext,
                CoroutineScope(SupervisorJob() + Dispatchers.Main),
            ).userDao(),
        )
    }

    // Provides Validators to application
    @Provides
    @Singleton
    fun provideValidators(): Validators {
        return Validators()
    }

//    @Provides
//    @Singleton
//    fun provideOfflinePlanetsRepository(): OfflinePlanetsRepository {
//        return AppDataContainer(
//            StudyPlanetApplication.appContext,
//            CoroutineScope(SupervisorJob() + Dispatchers.Main),
//        ).planetsRepository
//    }
//
//    @Provides
//    @Singleton
//    fun provideOfflineUsersRepository(): OfflineUsersRepository {
//        return AppDataContainer(
//            StudyPlanetApplication.appContext,
//            CoroutineScope(SupervisorJob() + Dispatchers.Main),
//        ).usersRepository
//    }

//    @Provides
//    @Singleton
//    fun provideAppContainer(): AppDataContainer {
//        return AppDataContainer(
//            StudyPlanetApplication.appContext,
//            CoroutineScope(SupervisorJob() + Dispatchers.Main),
//        )
//    }

    // for use cases
    @Provides
    @Singleton
    fun provideGetLocalPlanetsUseCase(
        repo: StudyPlanetRepository,
    ): GetLocalPlanetsUseCase {
        return GetLocalPlanetsUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetOnlinePlanetsUseCase(
        repo: StudyPlanetRepository,
    ): GetOnlinePlanetsUseCase {
        return GetOnlinePlanetsUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(
        repo: StudyPlanetRepository,
    ): LoginUseCase {
        return LoginUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideRegisterUseCase(
        repo: StudyPlanetRepository,
    ): RegisterUseCase {
        return RegisterUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideAuthenticateUseCase(
        repo: StudyPlanetRepository,
    ): AuthenticateUseCase {
        return AuthenticateUseCase(repo)
    }
}
