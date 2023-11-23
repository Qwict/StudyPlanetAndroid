package com.qwict.studyplanetandroid.presentation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.isUserAuthenticated
import com.qwict.studyplanetandroid.presentation.screens.DiscoveredPlanetsScreen
import com.qwict.studyplanetandroid.presentation.screens.ExplorerScreen
import com.qwict.studyplanetandroid.presentation.screens.MainScreen
import com.qwict.studyplanetandroid.presentation.screens.TimeSelectionScreen
import com.qwict.studyplanetandroid.presentation.screens.auth.LoginScreen
import com.qwict.studyplanetandroid.presentation.screens.auth.RegisterScreen
import com.qwict.studyplanetandroid.presentation.viewmodels.AuthViewModel
import com.qwict.studyplanetandroid.presentation.viewmodels.MainViewModel
import com.qwict.studyplanetandroid.presentation.viewmodels.StudyViewModel
import com.qwict.studyplanetandroid.presentation.viewmodels.UserViewModel

enum class StudyPlanetScreens(@StringRes val title: Int) {
    MainRoute(title = R.string.main_route),
    AuthenticationRoute(title = R.string.main_route),

    LoginScreen(title = R.string.title_login_screen),
    RegisterScreen(title = R.string.title_register_screen),

    MainScreen(title = R.string.title_main_screen),
    DiscoveredPlanetsScreen(title = R.string.title_discovered_planets_screen),
    TimeSelectionScreen(title = R.string.title_time_selection_screen),
    PlanetExplorerScreen(title = R.string.title_explorer_screen),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyPlanetNavigation(
    navController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    NavHost(
        navController = navController,
        startDestination = if (isUserAuthenticated) {
            StudyPlanetScreens.MainRoute.name
        } else {
            StudyPlanetScreens.AuthenticationRoute.name
        },
    ) {
        navigation(
            startDestination = StudyPlanetScreens.LoginScreen.name,
            route = StudyPlanetScreens.AuthenticationRoute.name,
        ) {
            composable(route = StudyPlanetScreens.LoginScreen.name) {
                val authViewModel = it.sharedViewModel<AuthViewModel>(navController)
                LoginScreen(
                    authState = authViewModel.state,
                    loginUser = { authViewModel.loginUser() },
                    navigateToRegisterScreen = {
                        navController.navigate(StudyPlanetScreens.RegisterScreen.name)
                    },
                    validationEvent = authViewModel.validationEvent,
                    onEvent = authViewModel::onEvent,
                    switchPasswordVisibility = { authViewModel.switchPasswordVisibility() },
                    clearValidationErrors = { authViewModel.clearValidationErrors() },
                )
            }
            composable(route = StudyPlanetScreens.RegisterScreen.name) {
                val authViewModel = it.sharedViewModel<AuthViewModel>(navController)
                RegisterScreen(
                    authState = authViewModel.state,
                    registerUser = { authViewModel.registerUser() },
                    navigateToLoginScreen = {
                        navController.navigate(StudyPlanetScreens.LoginScreen.name) {
                            popUpTo(StudyPlanetScreens.LoginScreen.name) { inclusive = true }
                        }
                    },
                    validationEvent = authViewModel.validationEvent,
                    onEvent = authViewModel::onEvent,
                    switchPasswordVisibility = { authViewModel.switchPasswordVisibility() },
                    clearValidationErrors = { authViewModel.clearValidationErrors() },
                )
            }
        }

        navigation(
            startDestination = StudyPlanetScreens.MainScreen.name,
            route = StudyPlanetScreens.MainRoute.name,
        ) {
            composable(route = StudyPlanetScreens.MainScreen.name) {
                val userViewModel = it.sharedViewModel<UserViewModel>(navController)
                MainScreen(
                    onStartExploringButtonClicked = {
                        navController.navigate(
                            StudyPlanetScreens.DiscoveredPlanetsScreen.name,
                        )
                    },
                    onDiscoverPlanetsButtonClicked = {
                        mainViewModel.isDiscovering = true
                        navController.navigate(StudyPlanetScreens.TimeSelectionScreen.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                    userState = userViewModel.state,
                )
            }
            composable(route = StudyPlanetScreens.TimeSelectionScreen.name) {
                val userViewModel = it.sharedViewModel<UserViewModel>(navController)
                TimeSelectionScreen(
                    onStartActionButtonClicked = {
                        navController.navigate(StudyPlanetScreens.PlanetExplorerScreen.name)
                    },
                    modifier = Modifier.fillMaxHeight(),
                    isDiscovering = mainViewModel.isDiscovering,
                    selectedTimeInMinutes = mainViewModel.selectedTimeInMinutes,
                    setSelectedTimeInMinutes = { mainViewModel.selectedTimeInMinutes = it },
                )
            }
            composable(route = StudyPlanetScreens.DiscoveredPlanetsScreen.name) {
                val userViewModel = it.sharedViewModel<UserViewModel>(navController)
                DiscoveredPlanetsScreen(
                    onMineButtonClicked = {
                        mainViewModel.selectedPlanet = it
                        mainViewModel.isDiscovering = false
                        navController.navigate(StudyPlanetScreens.TimeSelectionScreen.name)
                    },
                    modifier = Modifier.fillMaxHeight(),
                    getOnlinePlanets = { userViewModel.getOnlinePlanets() },
                    getLocalPlanets = { userViewModel.getLocalPlanets() },
                    userState = userViewModel.state,
                )
            }
            composable(route = StudyPlanetScreens.PlanetExplorerScreen.name) {
                val userViewModel = it.sharedViewModel<UserViewModel>(navController)
                val studyViewModel = it.sharedViewModel<StudyViewModel>(navController)
                BackHandler(true) {
                    // Or do nothing
                    Log.i("Application", "Clicked back")
                }
                ExplorerScreen(
                    onCancelStudyButtonClicked = {
                        navController.navigate(StudyPlanetScreens.MainScreen.name) {
                            popUpTo(StudyPlanetScreens.MainScreen.name) { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxHeight(),
                    isDiscovering = true,
                    selectedPlanet = mainViewModel.selectedPlanet,
                    selectedTimeInMinutes = mainViewModel.selectedTimeInMinutes,
                    studyState = studyViewModel.state,
                    startCountDown = { studyViewModel.startCountDown() },
                    resetAction = { studyViewModel.resetAction() },
                    startDiscovering = { studyViewModel.startDiscovering() },
                    stopDiscovering = { studyViewModel.stopDiscovering() },
                    startExploring = { studyViewModel.startExploring() },
                    stopExploring = { studyViewModel.stopExploring() },
                )
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}
