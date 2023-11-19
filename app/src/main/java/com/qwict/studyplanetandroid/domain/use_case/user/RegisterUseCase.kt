package com.qwict.studyplanetandroid.domain.use_case.user // ktlint-disable package-name

import android.util.Log
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.data.remote.dto.RegisterDto
import com.qwict.studyplanetandroid.data.repository.StudyPlanetRepository
import com.qwict.studyplanetandroid.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repo: StudyPlanetRepository,
) {
    operator fun invoke(
        name: String,
        email: String,
        password: String,
    ): Flow<Resource<User>> = flow {
        Log.i("RegisterUseCase", "invoke: $email, $password")
        try {
            emit(Resource.Loading())
            val authenticatedUser = repo.register(
                RegisterDto(
                    name = name,
                    email = email,
                    password = password,
                ),
            )
            if (authenticatedUser.validated) {
                emit(Resource.Success(insertLocalUserUseCase(authenticatedUser)))
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
