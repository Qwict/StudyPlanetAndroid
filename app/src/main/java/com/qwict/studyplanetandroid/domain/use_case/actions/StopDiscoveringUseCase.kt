package com.qwict.studyplanetandroid.domain.use_case.actions

import com.qwict.studyplanetandroid.common.AuthenticationSingleton.getUUID
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.isUserAuthenticated
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.validateUser
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.common.getEncryptedPreference
import com.qwict.studyplanetandroid.data.local.AppDataContainer
import com.qwict.studyplanetandroid.data.local.toPlanet
import com.qwict.studyplanetandroid.data.remote.dto.DiscoverActionDto
import com.qwict.studyplanetandroid.data.remote.dto.asDatabaseModel
import com.qwict.studyplanetandroid.domain.model.Planet
import com.qwict.studyplanetandroid.domain.repository.StudyPlanetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class StopDiscoveringUseCase @Inject constructor(
    private val repo: StudyPlanetRepository,
    private val container: AppDataContainer,
) {
    operator fun invoke(
        selectedTime: Int,
    ): Flow<Resource<Planet?>> = flow {
        try {
            emit(Resource.Loading())
            validateUser()
            if (isUserAuthenticated) {
                val discoveredPlanet = repo.stopDiscovering(DiscoverActionDto(selectedTime = selectedTime), token = getEncryptedPreference("token"))
                val currentDatabaseUser = container.usersRepository.getUserByUuid(getUUID())
                if (discoveredPlanet != null) {
                    val newDatabasePlanet = discoveredPlanet.asDatabaseModel(currentDatabaseUser.id, currentDatabaseUser.userUuid)
                    container.planetsRepository.insert(newDatabasePlanet)
                    emit(Resource.Success(newDatabasePlanet.toPlanet()))
                } else {
                    emit(Resource.Error("Failed to discover a new planet."))
                }
            } else {
                emit(Resource.Error("Failed to discover a new planet."))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            // No internet connection or whatever...
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
