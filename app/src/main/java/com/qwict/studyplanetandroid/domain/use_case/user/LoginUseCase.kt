package com.qwict.studyplanetandroid.domain.use_case.user // ktlint-disable package-name

import android.util.Log
import com.qwict.studyplanetandroid.common.AuthenticationSingleton
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.common.saveEncryptedPreference
import com.qwict.studyplanetandroid.data.remote.dto.LoginDto
import com.qwict.studyplanetandroid.data.remote.dto.asDomainModel
import com.qwict.studyplanetandroid.data.repository.StudyPlanetRepository
import com.qwict.studyplanetandroid.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repo: StudyPlanetRepository,
) {
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
            // No internet connection or whatever...
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
