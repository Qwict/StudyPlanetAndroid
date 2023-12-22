package com.qwict.studyplanetandroid.domain.use_case.planets // ktlint-disable package-name

import android.util.Log
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.getRemoteId
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.isUserAuthenticated
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.common.getEncryptedPreference
import com.qwict.studyplanetandroid.data.local.schema.toPlanet
import com.qwict.studyplanetandroid.data.remote.dto.asDomainModel
import com.qwict.studyplanetandroid.data.repository.StudyPlanetRepository
import com.qwict.studyplanetandroid.domain.model.Planet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use case for fetching local planets based on user authentication status.
 *
 * @property repo The repository responsible for interacting with data sources.
 */
class GetLocalPlanetsUseCase @Inject constructor(
    private val repo: StudyPlanetRepository,
) {
    /**
     * Invokes the use case to fetch local planets.
     *
     * @return Flow<Resource<List<Planet>>> representing the result of the operation.
     */
    operator fun invoke(): Flow<Resource<List<Planet>>> = flow {
        try {
            emit(Resource.Loading())
            // Will get the user's planets from the local database
            if (isUserAuthenticated) {
                val databasePlanets = repo.getPlanetsByRemoteId(getRemoteId())
                emit(Resource.Success(databasePlanets.map { it.toPlanet() }))
            } else {
                emit(Resource.Error("Login to see the planets that you have all ready discovered."))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}

/**
 * Use case for fetching online planets based on user authentication status.
 *
 * @property repo The repository responsible for interacting with data sources.
 */
class GetOnlinePlanetsUseCase @Inject constructor(
    private val repo: StudyPlanetRepository,
) {
    /**
     * Invokes the use case to fetch online planets.
     *
     * @return Flow<Resource<List<Planet>>> representing the result of the operation.
     */
    operator fun invoke(): Flow<Resource<List<Planet>>> = flow {
        Log.d("GetUserUseCase", "Getting online user to refresh planets")
        try {
            emit(Resource.Loading())
            // Will get the user's planets from the local database
            if (isUserAuthenticated) {
                val authenticatedUserDto = repo.authenticate(token = getEncryptedPreference("token"))
                emit(Resource.Success(authenticatedUserDto.asDomainModel().discoveredPlanets))
            } else {
                emit(Resource.Error("Login to see the planets that you have all ready discovered."))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}
