package com.qwict.studyplanetandroid.domain.use_case.actions

import com.qwict.studyplanetandroid.common.AuthenticationSingleton.isUserAuthenticated
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.validateUser
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.common.getEncryptedPreference
import com.qwict.studyplanetandroid.data.local.AppDataContainer
import com.qwict.studyplanetandroid.data.remote.dto.DiscoverActionDto
import com.qwict.studyplanetandroid.domain.model.User
import com.qwict.studyplanetandroid.domain.repository.StudyPlanetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class StartDiscoveringUseCase @Inject constructor(
    private val repo: StudyPlanetRepository,
    private val container: AppDataContainer,
) {
    operator fun invoke(
        selectedTime: Int,
    ): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())
            validateUser()
            if (isUserAuthenticated) {
                repo.startDiscovering(DiscoverActionDto(selectedTime = selectedTime), token = getEncryptedPreference("token"))
            } else if (getEncryptedPreference("token") == "expired") {
                emit(Resource.Error("Your access to the universe has expired. Please log in again to discover a new planet."))
            } else {
                emit(Resource.Error("Discovering a new planet will only be possible with access to the universe (the internets."))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            // No internet connection or whatever...
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
