package com.qwict.studyplanetandroid.ui.viewModels

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.qwict.studyplanetandroid.api.Api
import com.qwict.studyplanetandroid.data.Planet
import com.qwict.studyplanetandroid.data.StudyPlanetUiState
import com.qwict.studyplanetandroid.dto.User
import com.qwict.svkandroid.helper.clearEncryptedPreferences
import com.qwict.svkandroid.helper.saveEncryptedPreference
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.put
import retrofit2.Call
import retrofit2.Response
class MainViewModel() : ViewModel() {
    // TODO: what is this used for?
    private var _uiState = MutableStateFlow(
        StudyPlanetUiState(),
    )
    val uiState: StateFlow<StudyPlanetUiState> = _uiState.asStateFlow()
    var snackBarVisible = mutableStateOf(false)
    var isDiscovering = mutableStateOf(false)

    var selectedTime by mutableStateOf(0)
    var hours by mutableStateOf(0)
    var minutes by mutableStateOf(0)
    var seconds by mutableStateOf(0)
    var updatedTime by mutableStateOf(0)

    var selectedPlanet = Planet()
    var exploredPlanets = mutableListOf<Planet>()

    var user = User()
    var experienceBarProgress = mutableStateOf(user.experience.value / 10f)
    var userIsAuthenticated = mutableStateOf(false)
    var registerNewUser = mutableStateOf(false)
    var appJustLaunched = mutableStateOf(true)
    suspend fun countDown() {
        while (updatedTime > 0) {
            updatedTime -= 1000
            hours = (updatedTime / (1000 * 60 * 60)).toInt()
            minutes = (updatedTime % (1000 * 60 * 60) / (1000 * 60)).toInt()
            seconds = (updatedTime % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()
            delay(1000)
        }
    }

//    var user by mutableStateOf(User())
//    var exploredPlanets: MutableList<Planet> = mutableListOf<Planet>()
//    var selectedPlanet: Planet = Planet()
//    var selectedTime: Long = 0L
//
//    var appJustLaunched by mutableStateOf(true)
//    var userIsAuthenticated by mutableStateOf(false)

    private val TAG = "MainViewModel"
    private lateinit var context: Context

    fun register(email: MutableState<TextFieldValue>, password: MutableState<TextFieldValue>): Boolean {
        var success = false
        val body = buildJsonObject {
            put("name", email.value.text)
            put("email", email.value.text)
            put("password", password.value.text)
        }
        Log.i("MainViewModel", body.toString())

        Api.service.register(body).enqueue(object :
            retrofit2.Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.i("MainViewModel", response.code().toString())
                Log.i("MainViewModel", response.body().toString())
                if (response.isSuccessful) {
                    val token = response.body()!!["token"].toString().replace("\"", "")
                    val user = response.body()!!["user"]
                    createLocalUser(token, user as JsonObject)
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
        val body = buildJsonObject {
            put("email", email.value.text)
            put("password", password.value.text)
        }
        Log.i("MainViewModel", body.toString())

        Api.service.login(body).enqueue(object :
            retrofit2.Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    val token = response.body()!!["token"].toString().replace("\"", "")
                    val user = response.body()!!["user"]
                    createLocalUser(token, user as JsonObject)
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

    fun createLocalUser(token: String, jsonUser: JsonObject) {
        Log.i("MainViewModel", "Creating local user with jsonUser: $jsonUser")
        val planets = jsonUser["discoveredPlanets"] as JsonArray
        val experience = jsonUser["experience"].toString().toInt()

        user = User(
            token,
        )
        user.experience.value = experience

        planets.forEach() { planet ->
            user.discoveredPlanets.add(
                Planet(
                    planet.jsonObject["id"].toString().toInt(),
                    planet.jsonObject["name"].toString(),
                    planet.jsonObject["image"].toString().toInt(),
                ),
            )
        }

        userIsAuthenticated.value = true
        saveEncryptedPreference("token", user.token, context)
        Log.i("MainViewModel", user.token)
    }

    fun logout() {
        userIsAuthenticated.value = false
        user = User()
        clearEncryptedPreferences("token", context)
    }

    fun setContext(activityContext: Context) {
        context = activityContext
    }

    fun getUserById() {
        Api.service.getUserById(user.token, user.id).enqueue(object :
            retrofit2.Callback<JsonObject> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    Log.i("MainViewModel", "Got user")
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
        for (i in 0 until planets.size) {
            val jsonPlanet = planets[i] as JsonObject
            val planet = Planet(
                id = jsonPlanet["id"].toString().toInt(),
                name = jsonPlanet["name"].toString(),
                imageId = jsonPlanet["image"].toString().toInt(),
                // TODO: Why does this not work? (returns 0)
                //                    imageId = getDrawable(planet.getString("name")),
            )
            if (!user.discoveredPlanets.contains(planet)) {
                user.discoveredPlanets.add(planet)
            }
        }
        user.discoveredPlanets.forEach { planet ->
            Log.i("MainViewModel", "Discovered planet ${planet.name}, ${planet.imageId}, ${planet.id}")
        }
    }
    fun resetAction() {
        isDiscovering.value = false
        selectedTime = 0
        hours = 0
        minutes = 0
        seconds = 0
        updatedTime = 0
    }

    // TODO: my planets are saved in the database (with an image name/id) how could convert this parameter to a drawable?
    private fun getDrawable(name: String = "earth"): Int {
        return context.resources.getIdentifier(name, "drawable", context.packageName)
    }
}
