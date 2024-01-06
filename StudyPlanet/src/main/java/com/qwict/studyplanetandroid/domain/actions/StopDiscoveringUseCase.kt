@file:Suppress("ktlint:standard:package-name")

package com.qwict.studyplanetandroid.domain.actions

import com.qwict.studyplanetandroid.StudyPlanetApplication
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.data.StudyPlanetRepository
import com.qwict.studyplanetandroid.data.remote.dto.DiscoverActionDto
import com.qwict.studyplanetandroid.domain.model.ActionResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use case for stopping the process of discovering a new planet.
 *
 * @property repo The repository responsible for interacting with data sources.
 */
class StopDiscoveringUseCase
    @Inject
    constructor(
        private val repo: StudyPlanetRepository,
    ) {
        /**
         * Invokes the use case to stop the process of discovering a new planet.
         *
         * @param selectedTime The selected time for the discovery process.
         * @return Flow<Resource<ActionResponse>> representing the result of the operation.
         */
        operator fun invoke(selectedTime: Int): Flow<Resource<ActionResponse>> =
            flow {
                try {
                    emit(Resource.Loading())
                    StudyPlanetApplication.authSingleton.validateUser()
                    if (StudyPlanetApplication.authSingleton.isUserAuthenticated) {
                        val actionResponse = repo.stopDiscovering(DiscoverActionDto(selectedTime = selectedTime))
                        emit(Resource.Success(actionResponse))
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
