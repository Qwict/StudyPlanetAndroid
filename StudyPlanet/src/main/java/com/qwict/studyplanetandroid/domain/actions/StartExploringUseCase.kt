@file:Suppress("ktlint:standard:package-name")

package com.qwict.studyplanetandroid.domain.actions

import android.util.Log
import com.qwict.studyplanetandroid.StudyPlanetApplication
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.common.getEncryptedPreference
import com.qwict.studyplanetandroid.data.StudyPlanetRepository
import com.qwict.studyplanetandroid.data.remote.dto.ExploreActionDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use case for initiating the process of exploring a selected planet.
 *
 * @property repo The repository responsible for interacting with data sources.
 */
class StartExploringUseCase
    @Inject
    constructor(
        private val repo: StudyPlanetRepository,
    ) {
        /**
         * Invokes the use case to start the process of exploring a selected planet.
         *
         * @param selectedTimeInMillis The selected time for the exploration process.
         * @param selectedPlanetId The ID of the planet to explore.
         * @return Flow<Resource<Unit>> A flow of the result of the exploration process.
         */
        operator fun invoke(
            selectedTimeInMillis: Int,
            selectedPlanetId: Int,
        ): Flow<Resource<Unit>> =
            flow {
                try {
                    emit(Resource.Loading())
                    StudyPlanetApplication.authSingleton.validateUser()
                    if (StudyPlanetApplication.authSingleton.isUserAuthenticated) {
                        repo.startExploring(ExploreActionDto(planetId = selectedPlanetId, selectedTime = selectedTimeInMillis))
                    } else if (getEncryptedPreference("token") == "expired") {
                        emit(Resource.Error("Your access to the universe has expired. Please log in again to discover a new planet."))
                    } else {
                        emit(Resource.Error("Discovering a new planet will only be possible with access to the universe (the internets."))
                    }
                } catch (e: HttpException) {
                    emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
                } catch (e: IOException) {
                    Log.e("StartExploringUseCase", e.localizedMessage ?: "An unexpected error occurred")
                    emit(Resource.Error("Couldn't reach server. Check your internet connection."))
                }
            }
    }
