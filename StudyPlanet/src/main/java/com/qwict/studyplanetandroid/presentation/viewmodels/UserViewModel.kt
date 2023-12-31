package com.qwict.studyplanetandroid.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.domain.use_case.user.AuthenticateUseCase
import com.qwict.studyplanetandroid.presentation.viewmodels.states.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.math.ceil
import kotlin.math.log
import kotlin.math.pow

/**
 * ViewModel responsible for managing user-related functionality and state.
 *
 * The [UserViewModel] class is a Hilt-aware ViewModel that orchestrates interactions between the UI and the
 * underlying use cases for user authentication and planet data retrieval. It also handles the calculation of
 * user levels based on experience points.
 *
 * @property authenticateUseCase The use case responsible for authenticating the user and retrieving user data.
 */
@HiltViewModel
class UserViewModel @Inject constructor(
    private val authenticateUseCase: AuthenticateUseCase,
) : ViewModel() {
    var state by mutableStateOf(UserState())
        private set

    init {
        getUserWithToken()
    }

    sealed class ValidationEvent {
        data object Success : ValidationEvent()
    }

    /**
     * Initiates the user authentication process and retrieves user data based on the authentication result.
     *
     * This function uses the [authenticateUseCase] to authenticate the user and retrieve user data. It observes
     * the result of the process and updates the [UserState] based on the result:
     * - If the process is successful ([Resource.Success]), it updates the state with the authenticated user data
     *   and triggers the calculation of user levels using [levelCalculator].
     * - If an error occurs during the process ([Resource.Error]), it updates the state with the error message.
     * - During the loading phase ([Resource.Loading]), it updates the state to indicate loading.
     */
    private fun getUserWithToken() {
        authenticateUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    state = UserState(user = result.data!!)
                    levelCalculator(result.data.experience)
                }

                is Resource.Error -> {
                    state = state.copy(error = result.message ?: "An unexpected error occurred", isLoading = false)
                }

                is Resource.Loading -> {
                    state = state.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * Calculates user levels based on the provided experience points and updates the [UserState].
     *
     * This function uses logarithmic calculations to determine the current user level, experience points required
     * for the next level, and the progress towards the next level. It updates the [UserState] with the calculated values.
     *
     * @param experience The user's experience points.
     */
    private fun levelCalculator(experience: Int) {
        Log.d("AuthViewModel", "levelCalculator: ${log(experience.toDouble() / 60, 2.0)}")

        val currentLevel = ceil(log(experience.toDouble() / 60, 2.0))
        val experienceForCurrentLevel = (2.0.pow(currentLevel - 1) * 60)
        val experienceForNextLevel = (2.0.pow(currentLevel) * 60) - experience + experienceForCurrentLevel
        val experienceProgress = (experience - experienceForCurrentLevel) / (experienceForNextLevel)

        if (experience == 0) {
            state = state.copy(currentLevel = 0, experienceForNextLevel = 2)
        } else {
            state = state.copy(
                currentLevel = currentLevel.toInt(),
                experienceForNextLevel = experienceForNextLevel.toInt(),
                experienceProgress = experienceProgress.toFloat(),
            )
        }
        Log.d("AuthViewModel", "levelCalculator: ${state.currentLevel}, ${state.experienceForNextLevel}, ${state.experienceProgress}")
    }
}
