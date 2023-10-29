package com.qwict.studyplanetandroid.ui.viewModels

//import android.util.Log
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
//import androidx.lifecycle.viewModelScope
//import androidx.lifecycle.viewmodel.initializer
//import androidx.lifecycle.viewmodel.viewModelFactory
//import com.qwict.studyplanetandroid.StudyPlanetApplication
//import com.qwict.studyplanetandroid.api.Api
//import com.qwict.studyplanetandroid.data.Planet
//import com.qwict.studyplanetandroid.data.PlanetsRepository
//import com.qwict.studyplanetandroid.data.User
//import com.qwict.studyplanetandroid.data.UsersRepository
//import com.qwict.studyplanetandroid.presentation.user.StudyPlanetUiState
//import com.qwict.studyplanetandroid.presentation.user.UserUiState
//import com.qwict.svkandroid.helper.getEncryptedPreference
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.SharingStarted
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.firstOrNull
//import kotlinx.coroutines.flow.stateIn
//import kotlinx.coroutines.launch
//import kotlinx.serialization.json.JsonArray
//import kotlinx.serialization.json.JsonObject
//import kotlinx.serialization.json.buildJsonObject
//import kotlinx.serialization.json.put
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class UserViewModel(
//    private val planetsRepository: PlanetsRepository,
//    private val usersRepository: UsersRepository,
//) : ViewModel() {
//    var userUiState by mutableStateOf(UserUiState())
//        private set
//
//    private var _uiState = MutableStateFlow(
//        StudyPlanetUiState(),
//    )
//    val uiState: StateFlow<StudyPlanetUiState> = _uiState.asStateFlow()
//
////    ------------------------------------ Should be in uiState ------------------------------------
//    var selectedPlanet: Planet by mutableStateOf(Planet(userOwnerId = 0))
//    var isDiscovering = mutableStateOf(false)
//    var selectedTime by mutableStateOf(0)
//    var hours by mutableStateOf(0)
//    var minutes by mutableStateOf(0)
//    var seconds by mutableStateOf(0)
//    var updatedTime by mutableStateOf(0)
//
//    var registerNewUser = mutableStateOf(false)
//    var appJustLaunched = mutableStateOf(true)
//
////    var experienceBarProgress = mutableStateOf(
////        usersRepository.getUserById(getEncryptedPreference("userId").toInt()).experience,
////    )
////    -------------------------------------------
//
//    fun startExploring(selectedTime: Long) {
//        val body = buildJsonObject {
//            put("planetId", selectedPlanet.id.toString())
//            put("selectedTime", selectedTime.toString())
//        }
//
//        Log.i("ExplorerScreen", body.toString())
//        Api.service.startExploring(
////        "bearer ${viewModel.user.token}",
//            getEncryptedPreference("token"),
//            JsonObject(body),
//        ).enqueue(object : Callback<JsonObject> {
//            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//                if (response.isSuccessful) {
//                    Log.d("ExplorerScreen", "Started mining")
//                } else {
//                    Log.e("ExplorerScreen", "Failed to start mining")
//                }
//            }
//
//            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                startExploring(selectedTime)
//            }
//        })
//    }
//
//    fun stopExploring() {
//        Api.service.finishedExploration(getEncryptedPreference("token")).enqueue(object :
//            Callback<JsonObject> {
//            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//                if (response.isSuccessful) {
//                    Log.i("ExplorerScreen", "Stopped mining")
//                } else {
//                    Log.e("ExplorerScreen", "Failed to stop mining")
//                }
//            }
//
//            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
////                viewModel.snackBarVisible.value = true
//            }
//        })
//    }
//
//    fun startDiscovering(selectedTime: Int) {
//        Log.i("ExplorerScreen", "Started discovering")
//        Api.service.startDiscovery(
//            getEncryptedPreference("token"),
//            JsonObject(
//                buildJsonObject {
//                    put("selectedTime", selectedTime)
//                },
//            ),
//        ).enqueue(object : Callback<JsonObject> {
//            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//                if (response.isSuccessful) {
//                    Log.i("ExplorerScreen", "Started discovering")
//                } else {
////                TODO show snackbar
////                    snackBarVisible.value = true
//                    Log.e("ExplorerScreen", "Failed to start discovering")
//                }
//            }
//            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
////                TODO show snackbar (possible internet connection not working)
////                viewModel.snackBarVisible.value = true
//            }
//        })
//    }
//
//    fun stopDiscovering() {
//        Api.service.finishedDiscovery(getEncryptedPreference("token")).enqueue(object :
//            Callback<JsonObject> {
//            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//                if (response.isSuccessful) {
//                    val newPlanet = response.body()?.get("newPlanet") as JsonObject?
//                    if (newPlanet != null) {
//                        val planet = Planet(
//                            remoteId = newPlanet["id"].toString().replace("\"", "").toInt(),
//                            name = newPlanet["name"].toString().replace("\"", ""),
//                            userOwnerId = getEncryptedPreference("userId").toInt(),
//                            imageId = newPlanet["imageId"].toString().replace("\"", "").toInt(),
//                        )
//                        val scope = viewModelScope
//                        scope.launch {
//                            planetsRepository.insert(planet)
//                        }
//                    } else {
//                        Log.i("ExplorerScreen", "No planet was found, adding experience instead")
//                        viewModelScope.launch {
//                            val user = usersRepository.getUserById(getEncryptedPreference("userId").toInt()).firstOrNull() ?: User()
//                            user.experience += selectedTime / 1000 / 1000 / 60 // for production
//                            usersRepository.update(user)
//                        }
//                    }
//                    Log.i("ExplorerScreen", "Stopped discovering; it was a success")
//                    resetAction()
//                } else {
//                    Log.e("ExplorerScreen", "Failed to stop discovering")
//                }
//            }
//
//            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
////                snackBarVisible.value = true
//                Log.e("ExplorerScreen", "Failed to stop discovering: there was an a failure")
//            }
//        })
//    }
//
//    fun resetAction() {
//        isDiscovering.value = false
//        selectedTime = 0
//        hours = 0
//        minutes = 0
//        seconds = 0
//        updatedTime = 0
//    }
//    suspend fun countDown() {
//        while (updatedTime > 0) {
//            updatedTime -= 1000
//            hours = (updatedTime / (1000 * 60 * 60)).toInt()
//            minutes = (updatedTime % (1000 * 60 * 60) / (1000 * 60)).toInt()
//            seconds = (updatedTime % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()
//            delay(1000)
//        }
//    }
//
//    fun getUserById() {
//        Api.service.getUserById(getEncryptedPreference("token"), getEncryptedPreference("userId").toInt()).enqueue(object :
//            Callback<JsonObject> {
//            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//                if (response.isSuccessful) {
//                    Log.i("MainViewModel", "Got user with id: ${getEncryptedPreference("userId").toInt()}")
//                    Log.i("MainViewModel", response.body().toString())
//                    val planets = response.body()?.get("discoveredPlanets") as JsonArray
//                    setDiscoveredPlanets(planets)
//                } else {
//                    Log.e("MainViewModel", "Failed to get user")
//                }
//            }
//
//            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                Log.e("MainViewModel", "Failed to get user")
//            }
//        })
//    }
//
//    fun setDiscoveredPlanets(planets: JsonArray) {
//        val userOwnerId = getEncryptedPreference("userId").toInt()
//        for (i in 0 until planets.size) {
//            val jsonPlanet = planets[i] as JsonObject
//            val planet = Planet(
//                id = jsonPlanet["id"].toString().toInt(),
//                name = jsonPlanet["name"].toString(),
//                imageId = jsonPlanet["image"].toString().toInt(),
//                userOwnerId = userOwnerId,
//            )
//            viewModelScope.launch {
//                planetsRepository.insert(planet)
//            }
//        }
//    }
//
//    fun getUser(): StateFlow<User> {
//        return usersRepository.getUserById(getEncryptedPreference("userId").toInt())
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//                initialValue = User(),
//            )
//    }
//
//    fun getPlanets(): StateFlow<List<Planet>> {
//        return planetsRepository.getPlanetsByOwnerId(getEncryptedPreference("userId").toInt())
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//                initialValue = emptyList(),
//            )
//    }
//
//    companion object {
//        private const val TIMEOUT_MILLIS = 5_000L
//        val factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val application = (this[APPLICATION_KEY] as StudyPlanetApplication)
//                UserViewModel(
//                    planetsRepository = application.container.planetRepository,
//                    usersRepository = application.container.usersRepository,
//                )
//            }
//        }
//    }
//}
