package com.qwict.studyplanetandroid.ui.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.qwict.studyplanetandroid.StudyPlanetApplication
import com.qwict.studyplanetandroid.data.OldPlanet
import com.qwict.studyplanetandroid.data.PlanetsRepository
import com.qwict.studyplanetandroid.data.UsersRepository
import com.qwict.studyplanetandroid.ui.states.StudyPlanetUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
class MainViewModel(
    private val planetsRepository: PlanetsRepository,
    private val usersRepository: UsersRepository,
) : ViewModel() {
    private var _uiState = MutableStateFlow(
        StudyPlanetUiState(),
    )
    val uiState: StateFlow<StudyPlanetUiState> = _uiState.asStateFlow()

    var snackBarVisible = mutableStateOf(false)

    var selectedPlanet = OldPlanet()
    var exploredPlanets = mutableListOf<OldPlanet>()

//    var user by mutableStateOf(User())
//    var exploredPlanets: MutableList<Planet> = mutableListOf<Planet>()
//    var selectedPlanet: Planet = Planet()
//    var selectedTime: Long = 0L
//
//    var appJustLaunched by mutableStateOf(true)

    private val TAG = "MainViewModel"
//    private lateinit var context: Context

//    fun setContext(activityContext: Context) {
//        context = activityContext
//    }

    // TODO: my planets are saved in the database (with an image name/id) how could convert this parameter to a drawable?
    private fun getDrawable(name: String = "earth"): Int {
        return StudyPlanetApplication.appContext.resources.getIdentifier(name, "drawable", StudyPlanetApplication.appContext.packageName)
    }
}
