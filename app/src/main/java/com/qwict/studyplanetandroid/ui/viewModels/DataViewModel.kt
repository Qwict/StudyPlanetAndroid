package com.qwict.studyplanetandroid.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qwict.studyplanetandroid.data.Planet
import com.qwict.studyplanetandroid.data.PlanetsRepository
import com.qwict.studyplanetandroid.data.User
import com.qwict.studyplanetandroid.data.UsersRepository
import com.qwict.studyplanetandroid.ui.states.StudyPlanetUiState
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DataViewModel(
    private val planetsRepository: PlanetsRepository,
    private val usersRepository: UsersRepository,
) : ViewModel() {
    private var _uiState by mutableStateOf(StudyPlanetUiState())

//    init {
//        viewModelScope.launch {
//            _uiState = planetsRepository.getPlanets().map { StudyPlanetUiState(planets = it) }
//                .filterNotNull()
////            _uiState = usersRepository.getUserById(getEncryptedPreference("userId").toInt()).map { StudyPlanetUiState(user = it) }
////                .filterNotNull().first()
//        }
//    }
//    fun getPlanets(): List<Planet> {
//        return _uiState.planets
//    }
//
//    fun getUserById(id: Int): User {
//        return _uiState.user
//    }
//
//    companion object {
//        private const val TIMEOUT_MILLIS = 5_000L
//    }
}
