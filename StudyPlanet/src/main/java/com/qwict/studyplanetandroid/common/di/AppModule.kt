package com.qwict.studyplanetandroid.common.di

import com.qwict.studyplanetandroid.StudyPlanetApplication
import com.qwict.studyplanetandroid.common.AuthInterceptor
import com.qwict.studyplanetandroid.common.Constants.BASE_URL
import com.qwict.studyplanetandroid.data.StudyPlanetRepository
import com.qwict.studyplanetandroid.data.StudyPlanetRepositoryImpl
import com.qwict.studyplanetandroid.data.local.StudyPlanetDatabase
import com.qwict.studyplanetandroid.data.local.dao.PlanetDao
import com.qwict.studyplanetandroid.data.local.dao.UserDao
import com.qwict.studyplanetandroid.data.remote.StudyPlanetApi
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
     * It uses the provided [StudyPlanetApi] for making API requests, [UserDao] for accessing offline planet data,
     * and [PlanetDao] for managing offline user data.
     *
     * @param api The [StudyPlanetApi] instance used for making API requests.
     * @param userDao The [UserDao] instance used for accessing user data.
     * @param planetDao The [PlanetDao] instance used for accessing planet data.
     *
     * @return An instance of [StudyPlanetRepository] for handling StudyPlanet data with the specified dependencies.
     */
    @Provides
    @Singleton
    fun provideStudyPlanetRepository(
        api: StudyPlanetApi,
        userDao: UserDao,
        planetDao: PlanetDao,
    ): StudyPlanetRepository {
        return StudyPlanetRepositoryImpl(api, userDao, planetDao)
    }

    @Singleton
    @Provides
    fun provideStudyPlanetDatabase() = StudyPlanetDatabase.getDatabase(
        StudyPlanetApplication.appContext,
        CoroutineScope(SupervisorJob() + Dispatchers.Main),
    )

    @Singleton
    @Provides
    fun provideUserDao(db: StudyPlanetDatabase) = db.userDao()

    @Singleton
    @Provides
    fun providePlanetDao(db: StudyPlanetDatabase) = db.planetDao()

    @Provides
    @Singleton
    fun provideValidators(): Validators {
        return Validators()
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
