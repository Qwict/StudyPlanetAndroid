package com.qwict.studyplanetandroid.domain.use_case.actions // ktlint-disable package-name

import com.qwict.studyplanetandroid.common.AuthenticationSingleton.getRemoteId
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.isUserAuthenticated
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.validateUser
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.common.getEncryptedPreference
import com.qwict.studyplanetandroid.data.local.schema.toPlanet
import com.qwict.studyplanetandroid.data.remote.dto.DiscoverActionDto
import com.qwict.studyplanetandroid.data.remote.dto.asDatabaseModel
import com.qwict.studyplanetandroid.data.repository.StudyPlanetRepository
import com.qwict.studyplanetandroid.domain.model.Planet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class StopDiscoveringUseCase @Inject constructor(
    private val repo: StudyPlanetRepository,
) {
    operator fun invoke(
        selectedTime: Int,
    ): Flow<Resource<Planet?>> = flow {
        try {
            emit(Resource.Loading())
            validateUser()
            if (isUserAuthenticated) {
                val discoveredPlanet = repo.stopDiscovering(DiscoverActionDto(selectedTime = selectedTime), token = getEncryptedPreference("token"))
                val currentDatabaseUser = repo.getUserByRemoteId(getRemoteId())
                if (discoveredPlanet != null) {
                    val newDatabasePlanet = discoveredPlanet.asDatabaseModel(currentDatabaseUser.remoteId)
                    repo.insertPlanet(newDatabasePlanet)
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
