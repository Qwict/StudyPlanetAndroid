package com.qwict.studyplanetandroid.domain.use_case.get_planets

import android.util.Log
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.isUserAuthenticated
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.userUuid
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.common.getEncryptedPreference
import com.qwict.studyplanetandroid.data.local.AppDataContainer
import com.qwict.studyplanetandroid.data.local.toPlanet
import com.qwict.studyplanetandroid.data.remote.dto.asDatabaseModel
import com.qwict.studyplanetandroid.data.remote.dto.toDatabaseUserWithPlanets
import com.qwict.studyplanetandroid.domain.model.Planet
import com.qwict.studyplanetandroid.domain.repository.StudyPlanetRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLocalPlanetsUseCase @Inject constructor(
    private val repo: StudyPlanetRepository,
    private val container: AppDataContainer,
) {
    operator fun invoke(): Flow<Resource<List<Planet>>> = flow {
        Log.d("GetUserUseCase", "Getting user with uuid: $userUuid")
        try {
            emit(Resource.Loading())
            // Will get the user's planets from the local database
            if (isUserAuthenticated) {
                var databasePlanets = container.planetsRepository.getPlanetsByUserUuid(userUuid)
                emit(Resource.Success(databasePlanets.map { it.toPlanet() }))
            } else {
                emit(Resource.Error("Login to see the planets that you have all ready discovered."))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}

class GetOnlinePlanetsUseCase @Inject constructor(
    private val repo: StudyPlanetRepository,
    private val container: AppDataContainer,
) {
    operator fun invoke(): Flow<Resource<List<Planet>>> = flow {
        Log.d("GetUserUseCase", "Getting online user to refresh planets")
        try {
            emit(Resource.Loading())
            // Will get the user's planets from the local database
            if (isUserAuthenticated) {
                var databaseUserWithPlanets = repo.authenticate(token = getEncryptedPreference("token")).toDatabaseUserWithPlanets()
                var databasePlanets = databaseUserWithPlanets.planets
                container.planetsRepository.insertAll(databasePlanets)
                emit(Resource.Success(databasePlanets.map { it.toPlanet() }))
            } else {
                emit(Resource.Error("Login to see the planets that you have all ready discovered."))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}
