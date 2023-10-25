package com.qwict.studyplanetandroid.ui.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qwict.studyplanetandroid.data.DecodedUser
import com.qwict.studyplanetandroid.data.Planet
import com.qwict.studyplanetandroid.data.PlanetsRepository
import com.qwict.studyplanetandroid.data.StudyPlanetUiState
import com.qwict.studyplanetandroid.data.User
import com.qwict.studyplanetandroid.data.UsersRepository
import com.qwict.svkandroid.helper.saveEncryptedPreference
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

class DataViewModel(
    private val planetsRepository: PlanetsRepository,
    private val usersRepository: UsersRepository,
) : ViewModel() {
    private var _uiState by mutableStateOf(StudyPlanetUiState())

    init {
        viewModelScope.launch {
            _uiState = planetsRepository.getPlanets().map { StudyPlanetUiState(planets = it) }
                .filterNotNull().first()
//            _uiState = usersRepository.getUserById(getEncryptedPreference("userId").toInt()).map { StudyPlanetUiState(user = it) }
//                .filterNotNull().first()
        }
    }
    fun getPlanets(): List<Planet> {
        return _uiState.planets
    }

    fun getUserById(id: Int): User {
        return _uiState.user
    }

    fun savePlanet(planet: JsonObject) {
        viewModelScope.launch {
            planetsRepository.insert(planet = toPlanet(planet))
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    fun toPlanet(jsonPlanet: JsonObject): Planet {
        return Planet(
            id = jsonPlanet.jsonObject["id"].toString().toInt(),
            name = jsonPlanet.jsonObject["name"].toString(),
            imageId = jsonPlanet.jsonObject["image"].toString().toInt(),
        )
    }

    fun toUser(decodedUser: DecodedUser): User {
        return User(
            id = decodedUser.id,
            experience = decodedUser.experience.value,
        )
    }

    fun createLocalUser(token: String, jsonUser: JsonObject) {
        Log.i("MainViewModel", "Creating local user with jsonUser: $jsonUser")
        val jsonPlanets = jsonUser["discoveredPlanets"] as JsonArray
        val experience = jsonUser["experience"].toString().toInt()

        val decodedUser = DecodedUser(
            token,
        )
        saveEncryptedPreference("token", decodedUser.token)

        val planets = jsonPlanets.map { planet -> toPlanet(planet.jsonObject) }
        viewModelScope.launch {
            planetsRepository.insertAll(planets)
//            usersRepository.insert(toUser(decodedUser))
        }
    }
}
