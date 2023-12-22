package com.qwict.studyplanetandroid.common.di

import com.qwict.studyplanetandroid.StudyPlanetApplication
import com.qwict.studyplanetandroid.common.AuthInterceptor
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
import com.qwict.studyplanetandroid.domain.validator.Validators
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Dagger Hilt module responsible for providing singleton instances used throughout the application.
 *
 * This module is annotated with [Module] and is installed in [SingletonComponent].
 * It contains methods annotated with [Provides] to create and configure singleton instances
 * required for dependency injection using Dagger Hilt. These singletons play a crucial role in
 * various aspects of the StudyPlanet application.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides an instance of [StudyPlanetApi] using Retrofit.
     *
     * This function configures and creates a Retrofit instance for interacting with the StudyPlanet API.
     * It uses the [BASE_URL] as the base URL and sets up GsonConverterFactory for JSON serialization.
     * Additionally, it uses a custom OkHttpClient configured by [okhttpClient].
     *
     * @return An instance of [StudyPlanetApi] for making API requests.
     */
    @Provides
    @Singleton
    fun provideStudyPlanetApi(): StudyPlanetApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient()) // Add our Okhttp client
            .build()
            .create(StudyPlanetApi::class.java)
    }

    /**
     * Provides an instance of [OkHttpClient] configured with an authentication interceptor.
     *
     * This function creates and configures an OkHttpClient instance for handling HTTP requests.
     * It adds an [AuthInterceptor] to the OkHttpClient to include authentication headers in requests.
     * The OkHttpClient is configured as a singleton, meaning the same instance is reused throughout the application.
     *
     * @return An instance of [OkHttpClient] with authentication capabilities.
     */
    @Provides
    @Singleton
    fun okhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()
    }

//    @Provides
//    @Singleton
//    fun provideAuthenticationSingleton(): AuthenticationSingleton {
//        return AuthenticationSingleton()
//    }

    /**
     * Provides an instance of [StudyPlanetRepository] using the specified dependencies.
     *
     * This function creates and returns an implementation of [StudyPlanetRepository] by injecting the required dependencies.
     * It uses the provided [StudyPlanetApi] for making API requests, [OfflinePlanetsRepository] for accessing offline planet data,
     * and [OfflineUsersRepository] for managing offline user data.
     *
     * @param api The [StudyPlanetApi] instance used for making API requests.
     * @param planetsRepo The [OfflinePlanetsRepository] instance for accessing offline planet data.
     * @param usersRepository The [OfflineUsersRepository] instance for managing offline user data.
     *
     * @return An instance of [StudyPlanetRepository] for handling StudyPlanet data with the specified dependencies.
     */
    @Provides
    @Singleton
    fun provideStudyPlanetRepository(
        api: StudyPlanetApi,
        planetsRepo: OfflinePlanetsRepository,
        usersRepository: OfflineUsersRepository,
    ): StudyPlanetRepository {
        return StudyPlanetRepositoryImpl(api, usersRepository, planetsRepo)
    }

    /**
     * Provides an instance of [OfflinePlanetsRepository] backed by the StudyPlanetDatabase.
     *
     * This function creates and returns an implementation of [OfflinePlanetsRepository] that utilizes the StudyPlanetDatabase.
     * It initializes the database with the application context and a CoroutineScope, and retrieves the PlanetDao for data access.
     * The returned [OfflinePlanetsRepository] instance is configured as a singleton.
     *
     * @return An instance of [OfflinePlanetsRepository] using the StudyPlanetDatabase.
     */
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

    /**
     * Provides an instance of [OfflineUsersRepository] backed by the StudyPlanetDatabase.
     *
     * This function creates and returns an implementation of [OfflineUsersRepository] that utilizes the StudyPlanetDatabase.
     * It initializes the database with the application context and a CoroutineScope, and retrieves the UserDao for user data access.
     * The returned [OfflineUsersRepository] instance is configured as a singleton.
     *
     * @return An instance of [OfflineUsersRepository] using the StudyPlanetDatabase.
     */
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

    @Provides
    @Singleton
    fun provideValidators(): Validators {
        return Validators()
    }

    /**
     * Provides an instance of [GetLocalPlanetsUseCase] using the specified [StudyPlanetRepository].
     *
     * This function creates and returns an instance of [GetLocalPlanetsUseCase] by injecting the required [StudyPlanetRepository].
     * The [GetLocalPlanetsUseCase] is responsible for fetching local planet data using the provided repository.
     *
     * @param repo The [StudyPlanetRepository] instance used for retrieving local planet data.
     *
     * @return An instance of [GetLocalPlanetsUseCase] for fetching local planet data.
     */
    @Provides
    @Singleton
    fun provideGetLocalPlanetsUseCase(
        repo: StudyPlanetRepository,
    ): GetLocalPlanetsUseCase {
        return GetLocalPlanetsUseCase(repo)
    }

    /**
     * Provides an instance of [GetOnlinePlanetsUseCase] using the specified [StudyPlanetRepository].
     *
     * This function creates and returns an instance of [GetOnlinePlanetsUseCase] by injecting the required [StudyPlanetRepository].
     * The [GetOnlinePlanetsUseCase] is responsible for fetching online planet data using the provided repository.
     *
     * @param repo The [StudyPlanetRepository] instance used for retrieving online planet data.
     *
     * @return An instance of [GetOnlinePlanetsUseCase] for fetching online planet data.
     */
    @Provides
    @Singleton
    fun provideGetOnlinePlanetsUseCase(
        repo: StudyPlanetRepository,
    ): GetOnlinePlanetsUseCase {
        return GetOnlinePlanetsUseCase(repo)
    }

    /**
     * Provides an instance of [LoginUseCase] using the specified [StudyPlanetRepository].
     *
     * This function creates and returns an instance of [LoginUseCase] by injecting the required [StudyPlanetRepository].
     * The [LoginUseCase] is responsible for handling the login functionality using the provided repository.
     *
     * @param repo The [StudyPlanetRepository] instance used for handling login functionality.
     *
     * @return An instance of [LoginUseCase] for managing user login.
     */
    @Provides
    @Singleton
    fun provideLoginUseCase(
        repo: StudyPlanetRepository,
    ): LoginUseCase {
        return LoginUseCase(repo)
    }

    /**
     * Provides an instance of [RegisterUseCase] using the specified [StudyPlanetRepository].
     *
     * This function creates and returns an instance of [RegisterUseCase] by injecting the required [StudyPlanetRepository].
     * The [RegisterUseCase] is responsible for handling the user registration functionality using the provided repository.
     *
     * @param repo The [StudyPlanetRepository] instance used for handling user registration functionality.
     *
     * @return An instance of [RegisterUseCase] for managing user registration.
     */
    @Provides
    @Singleton
    fun provideRegisterUseCase(
        repo: StudyPlanetRepository,
    ): RegisterUseCase {
        return RegisterUseCase(repo)
    }

    /**
     * Provides an instance of [AuthenticateUseCase] using the specified [StudyPlanetRepository].
     *
     * This function creates and returns an instance of [AuthenticateUseCase] by injecting the required [StudyPlanetRepository].
     * The [AuthenticateUseCase] is responsible for handling user authentication using the provided repository.
     *
     * @param repo The [StudyPlanetRepository] instance used for handling user authentication functionality.
     *
     * @return An instance of [AuthenticateUseCase] for managing user authentication.
     */
    @Provides
    @Singleton
    fun provideAuthenticateUseCase(
        repo: StudyPlanetRepository,
    ): AuthenticateUseCase {
        return AuthenticateUseCase(repo)
    }
}
