package com.qwict.studyplanetandroid.domain.user

import android.util.Log
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.data.StudyPlanetRepository
import com.qwict.studyplanetandroid.data.remote.dto.RegisterDto
import com.qwict.studyplanetandroid.data.remote.dto.asDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use case for handling user registration.
 *
 * @param repo The repository providing access to the study planet data.
 */
class RegisterUseCase
    @Inject
    constructor(
        private val repo: StudyPlanetRepository,
    ) {
        /**
         * Invokes the registration use case with the provided user details.
         *
         * @param name The user's name.
         * @param email The user's email address.
         * @param password The user's password.
         * @return A [Flow] emitting [Resource] representing the registration operation result.
         */
        operator fun invoke(
            name: String,
            email: String,
            password: String,
        ): Flow<Resource<Unit>> =
            flow {
                Log.i("RegisterUseCase", "invoke: $email, $password")
                try {
                    emit(Resource.Loading())
                    val authenticatedUserDto =
                        repo.register(
                            RegisterDto(name = name, email = email, password = password),
                        )
                    if (authenticatedUserDto.validated) {
                        // Repository pattern insert user
                        repo.insertUser(authenticatedUserDto.asDatabaseModel())

                        // No planets must be inserted in registration...

                        // Final step: authenticate the user in the StudyPlanetApplication.authSingleton
                        saveTokenAndValidateUserUseCase(authenticatedUserDto.token)

                        emit(Resource.Success(Unit))
                    }
                } catch (e: HttpException) {
                    if (e.code() == 400) {
                        emit(Resource.Error("Make sure to fill out all fields."))
                    } else if (e.code() == 403) {
                        emit(Resource.Error("Registration is temporarily not available."))
                    } else if (e.code() == 409) {
                        emit(Resource.Error("$email is already in use."))
                    } else {
                        emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
                    }
                } catch (e: IOException) {
                    // No internet connection or whatever...
                    emit(Resource.Error("Couldn't reach server. Check your internet connection."))
                } catch (e: Exception) {
                    Log.e("LoginUseCase", "invoke: ${e.message}", e)
                    emit(Resource.Error("The developer didn't do his job..."))
                }
            }
    }
