package com.qwict.studyplanetandroid.domain.use_case.get_user

import android.util.Log
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.getUUID
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.data.local.AppDataContainer
import com.qwict.studyplanetandroid.data.remote.dto.RegisterDto
import com.qwict.studyplanetandroid.domain.model.User
import com.qwict.studyplanetandroid.domain.repository.StudyPlanetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repo: StudyPlanetRepository,
    private val container: AppDataContainer,
) {
    operator fun invoke(
        name: String,
        email: String,
        password: String,
    ): Flow<Resource<User>> = flow {
        Log.i("LoginUseCase", "invoke: $email, $password")
        try {
            emit(Resource.Loading())
            val authenticatedUser = repo.register(
                RegisterDto(
                    userUuid = getUUID(),
                    name = name,
                    email = email,
                    password = password,
                ),
            )
            if (authenticatedUser.validated) {
                emit(Resource.Success(InsertLocalUserUseCase(container)(authenticatedUser)))
            }
        } catch (e: HttpException) {
            if (e.code() == 400) {
                emit(Resource.Error("Make sure to fill out all fields."))
            } else if (e.code() == 403) {
                emit(Resource.Error("Invalid credentials."))
            }
        } catch (e: IOException) {
            // No internet connection or whatever...
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}