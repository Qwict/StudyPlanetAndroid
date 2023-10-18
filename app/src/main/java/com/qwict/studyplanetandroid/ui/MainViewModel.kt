package com.qwict.studyplanetandroid.ui

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.qwict.studyplanetandroid.data.Planet
import com.qwict.studyplanetandroid.data.StudyPlanetUiState
import com.qwict.studyplanetandroid.dto.User
import com.qwict.studyplanetandroid.helper.RetrofitSingleton
import com.qwict.svkandroid.helper.clearEncryptedPreferences
import com.qwict.svkandroid.helper.saveEncryptedPreference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(StudyPlanetUiState())
    val uiState: StateFlow<StudyPlanetUiState> = _uiState.asStateFlow()

    // TODO: what is this used for?
    val snackBarHostState = SnackbarHostState()

    var user by mutableStateOf(User())

    var exploredPlanets: MutableList<Planet> = mutableListOf<Planet>()
    var selectedPlanet: Planet = Planet()
    var selectedTime: Long = 0L

    fun setSelectedPlanet(planet: Planet) {
        _uiState.update { currentState ->
            currentState.copy(selectedPlanet = planet)
        }
    }

    var appJustLaunched by mutableStateOf(true)
    var userIsAuthenticated by mutableStateOf(false)

    private val TAG = "MainViewModel"
    private lateinit var context: Context

    fun login(email: MutableState<TextFieldValue>, password: MutableState<TextFieldValue>): Boolean {
        var success = false
        val body = mapOf(
            "email" to email.value.text,
            "password" to password.value.text,
        )
        Log.i("MainViewModel", body.toString())

        RetrofitSingleton.getApiService().login(body).enqueue(object :
            retrofit2.Callback<ResponseBody> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.i("MainViewModel", "Logged in")
                    val stringResponse = response.body()?.string()
                    var json = JSONObject(stringResponse)
//                    user.token = json.getString("token")
                    user = User(
                        json.getString("token"),
                    )
                    userIsAuthenticated = true
//                    Not sure if this is needed (because this also happens in MainActivity onPause)
                    saveEncryptedPreference("token", user.token, context)
                    Log.i("MainViewModel", user.token)
                    success = true
                    try {
                        getUserById()
                    } catch (e: Exception) {
                        Log.e("MainViewModel", "Failed to get user $success")
                    }
                } else {
                    Log.e("MainViewModel", "Failed to Login")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("MainViewModel", "Failed to Login")
            }
        })
        return success
    }

    fun logout() {
        userIsAuthenticated = false
        user = User()
        clearEncryptedPreferences("token", context)
    }

    fun setContext(activityContext: Context) {
        context = activityContext
    }

    fun getUserById() {
        RetrofitSingleton.getApiService().getUserById(user.token, user.id).enqueue(object :
            retrofit2.Callback<ResponseBody> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.i("MainViewModel", "Got user")
                    val stringResponse = response.body()?.string()
                    var json = JSONObject(stringResponse)
                    Log.i("MainViewModel", json.toString())
                    val planets = json.getJSONArray("discoveredPlanets")
                    setDiscoveredPlanets(planets)
                } else {
                    Log.e("MainViewModel", "Failed to get user")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("MainViewModel", "Failed to get user")
            }
        })
    }

    fun setDiscoveredPlanets(planets: JSONArray) {
        for (i in 0 until planets.length()) {
            val planet = planets.get(i) as JSONObject
            user.discoveredPlanets.add(
                Planet(
                    id = planet.getInt("id"),
                    name = planet.getString("name"),
                    imageId = planet.getInt("image"),
                    // TODO: Why does this not work? (returns 0)
//                    imageId = getDrawable(planet.getString("name")),
                ),
            )
        }
        user.discoveredPlanets.forEach { planet ->
            Log.i("MainViewModel", "Discovered planet ${planet.name}, ${planet.imageId}, ${planet.id}")
        }
    }

    // TODO: my planets are saved in the database (with an image name/id) how could convert this parameter to a drawable?
    private fun getDrawable(name: String = "earth"): Int {
        return context.resources.getIdentifier(name, "drawable", context.packageName)
    }
}
