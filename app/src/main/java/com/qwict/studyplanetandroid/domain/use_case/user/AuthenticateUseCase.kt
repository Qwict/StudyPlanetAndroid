package com.qwict.studyplanetandroid.domain.use_case.user // ktlint-disable package-name

import android.util.Log
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.getRemoteId
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.isUserAuthenticated
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.validateUser
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.common.getEncryptedPreference
import com.qwict.studyplanetandroid.data.local.schema.toUser
import com.qwict.studyplanetandroid.data.repository.StudyPlanetRepository
import com.qwict.studyplanetandroid.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthenticateUseCase @Inject constructor(
    private val repo: StudyPlanetRepository,
) {
    operator fun invoke(): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())
            validateUser()
            if (isUserAuthenticated) {
                val databaseUser = repo.getUserByRemoteId(getRemoteId())
                try {
                    emit(Resource.Success(databaseUser.toUser()))
                    emit(Resource.Loading())
                } catch (e: Exception) {
                    Log.e("AuthenticateUseCase", "Failed to get local user, will look online with token Error: ${e.localizedMessage}")
                }
                val authenticatedUser = repo.authenticate(token = getEncryptedPreference("token"))
                Log.i("AuthenticateUseCase", "invoke: ${authenticatedUser.validated}")
                if (authenticatedUser.validated) {
                    emit(Resource.Success(insertLocalUserUseCase(authenticatedUser)))
                }
            } else if (getEncryptedPreference("token") == "expired") {
                emit(Resource.Error("Your login has expired. Please log in again."))
            } else {
                emit(Resource.Error("You are not logged in."))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            // No internet connection or whatever...
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            Log.e("LoginUseCase", "invoke: ${e.message}", e)
            emit(Resource.Error("The developer didn't do his job..."))
        }
    }
}
