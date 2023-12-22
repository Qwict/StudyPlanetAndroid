package com.qwict.studyplanetandroid.domain.use_case.user // ktlint-disable package-name

import android.util.Log
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.data.remote.dto.LoginDto
import com.qwict.studyplanetandroid.data.repository.StudyPlanetRepository
import com.qwict.studyplanetandroid.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use case for handling user login.
 *
 * @param repo The repository providing access to the study planet data.
 */
class LoginUseCase @Inject constructor(
    private val repo: StudyPlanetRepository,
) {
    /**
     * Invokes the login use case with the provided email and password.
     *
     * @param email The user's email address.
     * @param password The user's password.
     * @return A [Flow] emitting [Resource] representing the login operation result.
     */
    operator fun invoke(
        email: String,
        password: String,
    ): Flow<Resource<User>> = flow {
        Log.i("LoginUseCase", "invoke: $email, $password")
        emit(Resource.Loading())
        try {
            val authenticatedUserDto = repo.login(LoginDto(email = email, password = password))

            if (authenticatedUserDto.validated) {
                emit(Resource.Success(insertLocalUserUseCase(authenticatedUserDto)))
            }
        } catch (e: HttpException) {
            Log.e("LoginUseCase", "invoke: ${e.code()}", e)
            if (e.code() == 400) {
                emit(Resource.Error("Make sure to fill out all fields."))
            } else if (e.code() == 403) {
                emit(Resource.Error("Invalid credentials."))
            } else {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            }
        } catch (e: IOException) {
            Log.e("LoginUseCase", e.message ?: "An unexpected error occurred", e)
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            Log.e("LoginUseCase", "invoke: ${e.message}", e)
            emit(Resource.Error("The developer didn't do his job..."))
        }
    }
}