package com.qwict.studyplanetandroid.domain.use_case.get_user

import android.util.Log
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.isUserAuthenticated
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.validateUser
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.common.getEncryptedPreference
import com.qwict.studyplanetandroid.data.local.AppDataContainer
import com.qwict.studyplanetandroid.domain.model.User
import com.qwict.studyplanetandroid.domain.repository.StudyPlanetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthenticateUseCase @Inject constructor(
    private val repo: StudyPlanetRepository,
    private val container: AppDataContainer,
) {
    operator fun invoke(): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())
            validateUser()
            if (isUserAuthenticated) {
                val authenticatedUser = repo.authenticate(token = getEncryptedPreference("token"))
                Log.i("AuthenticateUseCase", "invoke: ${authenticatedUser.validated}")
                if (authenticatedUser.validated) {
                    emit(Resource.Success(InsertLocalUserUseCase(container)(authenticatedUser)))
                }
            } else {
                emit(Resource.Error("Your login has expired. Please log in again."))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            // No internet connection or whatever...
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
