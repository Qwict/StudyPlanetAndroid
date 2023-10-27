package com.qwict.studyplanetandroid.ui.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qwict.studyplanetandroid.api.Api
import com.qwict.studyplanetandroid.data.Planet
import com.qwict.studyplanetandroid.data.PlanetsRepository
import com.qwict.studyplanetandroid.data.User
import com.qwict.studyplanetandroid.data.UsersRepository
import com.qwict.studyplanetandroid.data.toPlanet
import com.qwict.studyplanetandroid.service.tokenIsValid
import com.qwict.studyplanetandroid.ui.states.UserUiState
import com.qwict.svkandroid.helper.clearEncryptedPreferences
import com.qwict.svkandroid.helper.getEncryptedPreference
import com.qwict.svkandroid.helper.saveEncryptedPreference
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.put
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(
    private val planetsRepository: PlanetsRepository,
    private val usersRepository: UsersRepository,
) : ViewModel() {
    var userUiState by mutableStateOf(UserUiState())
        private set

//    ------------------------------------ Should be in uiState ------------------------------------
    var selectedPlanet: Planet by mutableStateOf(Planet(userOwnerId = 0))
    var isDiscovering = mutableStateOf(false)
    var selectedTime by mutableStateOf(0)
    var hours by mutableStateOf(0)
    var minutes by mutableStateOf(0)
    var seconds by mutableStateOf(0)
    var updatedTime by mutableStateOf(0)

    var registerNewUser = mutableStateOf(false)
    var appJustLaunched = mutableStateOf(true)

//    var experienceBarProgress = mutableStateOf(
//        usersRepository.getUserById(getEncryptedPreference("userId").toInt()).experience,
//    )
    var userIsAuthenticated by mutableStateOf(false)
//    -------------------------------------------

    init {
        Log.d("MainViewModel", "init")
        authenticationCheckWithToken()
    }

    fun register(email: MutableState<TextFieldValue>, password: MutableState<TextFieldValue>): Boolean {
        var success = false
        val body = buildJsonObject {
            put("name", email.value.text)
            put("email", email.value.text)
            put("password", password.value.text)
        }
        Log.i("MainViewModel", body.toString())

        Api.service.register(body).enqueue(object :
            Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.d("MainViewModel", response.code().toString())
                Log.d("MainViewModel", response.body().toString())
                if (response.isSuccessful) {
                    val token = response.body()!!["token"].toString().replace("\"", "")
                    val jsonUser = response.body()!!["user"]
                    createLocalUser(token, jsonUser as JsonObject)
                    success = true
                } else {
                    Log.e("MainViewModel", "Failed to Login")
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("MainViewModel", "Failed to Login, Failure with message: ${t.message}")
            }
        })
        return success
    }

    fun login(email: MutableState<TextFieldValue>, password: MutableState<TextFieldValue>): Boolean {
        var success = false

        Api.service.login(
            buildJsonObject {
                put("email", email.value.text)
                put("password", password.value.text)
            },
        ).enqueue(object :
            Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    val token = response.body()!!["token"].toString().replace("\"", "")
                    val jsonUser = response.body()!!["user"]
                    createLocalUser(token, jsonUser as JsonObject)
                    userIsAuthenticated = true
                    success = true
                } else {
                    Log.e("MainViewModel", "Failed to Login response was not successful")
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("MainViewModel", "Failed to Login")
            }
        })
        return success
    }

    fun logout() {
        userIsAuthenticated = false
        clearEncryptedPreferences("token")
        clearEncryptedPreferences("userId")
        clearEncryptedPreferences("email")
    }

    fun createLocalUser(token: String, jsonUser: JsonObject) {
        Log.i("MainViewModel", "Creating local user with jsonUser: $jsonUser")
        val jsonPlanets = jsonUser["discoveredPlanets"] as JsonArray
        val experience = jsonUser["experience"].toString().toInt()
        val email = jsonUser["email"].toString().replace("\"", "")
        val id = jsonUser["id"].toString().toInt()

        Log.d("ApiViewModel", "Creating local user with experience: $experience, id: $id and ${jsonPlanets.size} planets}")
        saveEncryptedPreference("token", token)
        saveEncryptedPreference("userId", id.toString())
        saveEncryptedPreference("email", email)

        val planets = jsonPlanets.map { planet -> toPlanet(planet.jsonObject, id) }
        viewModelScope.launch {
            planetsRepository.insertAll(planets)
            usersRepository.insert(
                User(
                    id = id,
                    experience = experience,
                ),
            )
        }
    }

    fun startExploring(selectedTime: Long) {
        val body = buildJsonObject {
            put("planetId", selectedPlanet.id.toString())
            put("selectedTime", selectedTime.toString())
        }

        Log.i("ExplorerScreen", body.toString())
        Api.service.startExploring(
//        "bearer ${viewModel.user.token}",
            getEncryptedPreference("token"),
            JsonObject(body),
        ).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    Log.d("ExplorerScreen", "Started mining")
                } else {
                    Log.e("ExplorerScreen", "Failed to start mining")
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                startExploring(selectedTime)
            }
        })
    }

    fun stopExploring() {
        Api.service.finishedExploration(getEncryptedPreference("token")).enqueue(object :
            Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    Log.i("ExplorerScreen", "Stopped mining")
                } else {
                    Log.e("ExplorerScreen", "Failed to stop mining")
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                viewModel.snackBarVisible.value = true
            }
        })
    }

    fun startDiscovering(selectedTime: Int) {
        Log.i("ExplorerScreen", "Started discovering")
        Api.service.startDiscovery(
            getEncryptedPreference("token"),
            JsonObject(
                buildJsonObject {
                    put("selectedTime", selectedTime)
                },
            ),
        ).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    Log.i("ExplorerScreen", "Started discovering")
                } else {
//                TODO show snackbar
//                    snackBarVisible.value = true
                    Log.e("ExplorerScreen", "Failed to start discovering")
                }
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                TODO show snackbar (possible internet connection not working)
//                viewModel.snackBarVisible.value = true
            }
        })
    }

    fun stopDiscovering() {
        Api.service.finishedDiscovery(getEncryptedPreference("token")).enqueue(object :
            Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    val newPlanet = response.body()?.get("newPlanet") as JsonObject?
                    if (newPlanet != null) {
                        val planet = Planet(
                            remoteId = newPlanet["id"].toString().replace("\"", "").toInt(),
                            name = newPlanet["name"].toString().replace("\"", ""),
                            userOwnerId = getEncryptedPreference("userId").toInt(),
                            imageId = newPlanet["imageId"].toString().replace("\"", "").toInt(),
                        )
                        val scope = viewModelScope
                        scope.launch {
                            planetsRepository.insert(planet)
                        }
                    } else {
                        Log.i("ExplorerScreen", "No planet was found, adding experience instead")
                        viewModelScope.launch {
                            val user = usersRepository.getUserById(getEncryptedPreference("userId").toInt()).firstOrNull() ?: User()
                            user.experience += selectedTime / 1000 / 1000 / 60 // for production
                            usersRepository.update(user)
                        }
                    }
                    Log.i("ExplorerScreen", "Stopped discovering; it was a success")
                    resetAction()
                } else {
                    Log.e("ExplorerScreen", "Failed to stop discovering")
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                snackBarVisible.value = true
                Log.e("ExplorerScreen", "Failed to stop discovering: there was an a failure")
            }
        })
    }

    fun resetAction() {
        isDiscovering.value = false
        selectedTime = 0
        hours = 0
        minutes = 0
        seconds = 0
        updatedTime = 0
    }
    suspend fun countDown() {
        while (updatedTime > 0) {
            updatedTime -= 1000
            hours = (updatedTime / (1000 * 60 * 60)).toInt()
            minutes = (updatedTime % (1000 * 60 * 60) / (1000 * 60)).toInt()
            seconds = (updatedTime % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()
            delay(1000)
        }
    }

    fun getUserById() {
        Api.service.getUserById(getEncryptedPreference("token"), getEncryptedPreference("userId").toInt()).enqueue(object :
            Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    Log.i("MainViewModel", "Got user with id: ${getEncryptedPreference("userId").toInt()}")
                    Log.i("MainViewModel", response.body().toString())
                    val planets = response.body()?.get("discoveredPlanets") as JsonArray
                    setDiscoveredPlanets(planets)
                } else {
                    Log.e("MainViewModel", "Failed to get user")
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("MainViewModel", "Failed to get user")
            }
        })
    }

    fun setDiscoveredPlanets(planets: JsonArray) {
        val userOwnerId = getEncryptedPreference("userId").toInt()
        for (i in 0 until planets.size) {
            val jsonPlanet = planets[i] as JsonObject
            val planet = Planet(
                id = jsonPlanet["id"].toString().toInt(),
                name = jsonPlanet["name"].toString(),
                imageId = jsonPlanet["image"].toString().toInt(),
                userOwnerId = userOwnerId,
            )
            viewModelScope.launch {
                planetsRepository.insert(planet)
            }
        }
    }

    fun authenticationCheckWithToken() {
        val token = getEncryptedPreference("token")
        if (token != null && token != "") {
            if (tokenIsValid(getEncryptedPreference("token"))) {
                userIsAuthenticated = true
            } else {
                Log.i("EncryptionService", "onResume: token was found but is invalid")
                logout()
            }
        } else {
            Log.i("EncryptionService", "onResume: token is null or empty")
            logout()
        }
    }

    fun getUser(): StateFlow<User> {
        return usersRepository.getUserById(getEncryptedPreference("userId").toInt())
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = User(),
            )
    }

    fun getPlanets(): StateFlow<List<Planet>> {
        return planetsRepository.getPlanetsByOwnerId(getEncryptedPreference("userId").toInt())
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = emptyList(),
            )
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
