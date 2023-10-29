package com.qwict.studyplanetandroid.domain.use_case.get_user

import com.qwict.studyplanetandroid.common.AuthenticationSingleton
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.data.remote.dto.AuthenticationDto
import com.qwict.studyplanetandroid.data.remote.dto.LoginDto
import com.qwict.studyplanetandroid.data.remote.dto.toUser
import com.qwict.studyplanetandroid.domain.model.User
import com.qwict.studyplanetandroid.domain.repository.StudyPlanetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserWithTokenUseCase @Inject constructor(
    private val repo: StudyPlanetRepository,
) {
    operator fun invoke(): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())
            val authenticatedUser = repo.authenticate(AuthenticationDto(token = AuthenticationSingleton.token))
            AuthenticationSingleton.isUserAuthenticated = true
            AuthenticationSingleton.token = authenticatedUser.token
            emit(Resource.Success(authenticatedUser.toUser()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            // No internet connection or whatever...
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
