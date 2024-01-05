package com.qwict.studyplanetandroid.presentation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.StudyPlanetApplication
import com.qwict.studyplanetandroid.presentation.screens.DiscoveredPlanetsScreen
import com.qwict.studyplanetandroid.presentation.screens.ExplorerScreen
import com.qwict.studyplanetandroid.presentation.screens.MainScreen
import com.qwict.studyplanetandroid.presentation.screens.TimeSelectionScreen
import com.qwict.studyplanetandroid.presentation.screens.auth.LoginScreen
import com.qwict.studyplanetandroid.presentation.screens.auth.RegisterScreen
import com.qwict.studyplanetandroid.presentation.viewmodels.AuthViewModel
import com.qwict.studyplanetandroid.presentation.viewmodels.SelectedPlanetViewModel

enum class StudyPlanetScreens(
    @StringRes val title: Int,
) {
    MainRoute(title = R.string.main_route),
    AuthenticationRoute(title = R.string.main_route),

    LoginScreen(title = R.string.title_login_screen),
    RegisterScreen(title = R.string.title_register_screen),

    MainScreen(title = R.string.title_main_screen),
    DiscoveredPlanetsScreen(title = R.string.title_discovered_planets_screen),
    TimeSelectionScreen(title = R.string.title_time_selection_screen),
    PlanetExplorerScreen(title = R.string.title_explorer_screen),
}

/**
 * Composable function defining the navigation structure for the StudyPlanet application.
 *
 * The [StudyPlanetNavigation] composable function sets up the navigation using the Jetpack Navigation
 * library. It defines the destinations, routes, and associated composable screens for the application.
 *
 * @param navController The navigation controller managing the navigation flow.
 */
@Composable
fun StudyPlanetNavigation(navController: NavHostController) {
    /**
     * Configures the navigation host for the StudyPlanet application.
     *
     * The [NavHost] composable function sets up the navigation host using the Jetpack Navigation library.
     * It specifies the navigation controller and the start destination based on whether the user is authenticated.
     *
     * @param navController The navigation controller managing the navigation flow.
     */
    NavHost(
        navController = navController,
        startDestination =
            if (StudyPlanetApplication.authSingleton.isUserAuthenticated) {
                StudyPlanetScreens.MainRoute.name
            } else {
                StudyPlanetScreens.AuthenticationRoute.name
            },
    ) {
        /**
         * Defines the navigation structure for the authentication flow in the StudyPlanet application.
         *
         * The [navigation] composable function is used to define the navigation structure for the authentication flow.
         * It specifies the start destination and route for the authentication route in the navigation graph.
         *
         */
        navigation(
            startDestination = StudyPlanetScreens.LoginScreen.name,
            route = StudyPlanetScreens.AuthenticationRoute.name,
        ) {
            /**
             * Composable function representing the login screen in the StudyPlanet application.
             *
             * The [LoginScreen] composable function is responsible for rendering the login screen UI and handling
             * user interactions related to the login process.
             *
             */
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

            /**
             * Composable function representing the registration screen in the StudyPlanet application.
             *
             * The [RegisterScreen] composable function is responsible for rendering the registration screen UI and handling
             * user interactions related to the registration process.
             *
             */
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

        /**
         * Defines the navigation structure for the main content flow in the StudyPlanet application.
         *
         * The [navigation] composable function is used to define the navigation structure for the main content flow.
         * It specifies the start destination and route for the main route in the navigation graph.
         *
         */
        navigation(
            startDestination = StudyPlanetScreens.MainScreen.name,
            route = StudyPlanetScreens.MainRoute.name,
        ) {
            /**
             * Composable function representing the main screen in the StudyPlanet application.
             *
             * The [MainScreen] composable function is responsible for rendering the main screen content and handling user interactions.
             *
             */
            composable(route = StudyPlanetScreens.MainScreen.name) {
                val selectedPlanetViewModel = it.sharedViewModel<SelectedPlanetViewModel>(navController)
                selectedPlanetViewModel.reset()
                MainScreen(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(R.dimen.padding_medium)),
                )
            }

            /**
             * Composable function representing the time selection screen in the StudyPlanet application.
             *
             * The [TimeSelectionScreen] composable function is responsible for rendering the time selection screen content
             * and handling user interactions related to selecting exploration time.
             *
             */
            composable(
                route = StudyPlanetScreens.TimeSelectionScreen.name,
                arguments =
                    listOf(
                        navArgument("selectedPlanet") {
                            defaultValue = 0
                            type = NavType.IntType
                        },
                    ),
            ) {
                val selectedPlanetViewModel = it.sharedViewModel<SelectedPlanetViewModel>(navController)
                TimeSelectionScreen(
                    onStartActionButtonClicked = {
                        navController.navigate(StudyPlanetScreens.PlanetExplorerScreen.name)
                    },
                    modifier = Modifier.fillMaxHeight(),
                    isDiscovering = selectedPlanetViewModel.isDiscovering,
                    selectedTimeInMinutes = selectedPlanetViewModel.selectedTimeInMinutes,
                    setSelectedTimeInMinutes = { selectedPlanetViewModel.selectedTimeInMinutes = it },
                )
            }

            /**
             * Composable function representing the screen displaying discovered planets in the StudyPlanet application.
             *
             * The [DiscoveredPlanetsScreen] composable function is responsible for rendering the screen content related
             * to discovered planets and handling user interactions.
             *
             */
            composable(route = StudyPlanetScreens.DiscoveredPlanetsScreen.name) {
                val selectedPlanetViewModel = it.sharedViewModel<SelectedPlanetViewModel>(navController)
                DiscoveredPlanetsScreen(
                    modifier = Modifier.fillMaxHeight(),
                    navigateToTimeSelectionScreen = {
                        selectedPlanetViewModel.selectedPlanet = it
                        selectedPlanetViewModel.isDiscovering = false
                        navController.navigate(StudyPlanetScreens.TimeSelectionScreen.name)
                    },
                )
            }

            /**
             * Composable function representing the planet exploration screen in the StudyPlanet application.
             *
             * The [ExplorerScreen] composable function is responsible for rendering the screen content related
             * to exploring a selected planet and handling user interactions during exploration.
             *
             */
            composable(route = StudyPlanetScreens.PlanetExplorerScreen.name) {
                val selectedPlanetViewModel = it.sharedViewModel<SelectedPlanetViewModel>(navController)
                BackHandler(true) {
                    Log.i("Application", "Clicked back")
                }
                ExplorerScreen(
                    navigateBackToMainScreen = {
                        navController.navigate(StudyPlanetScreens.MainScreen.name) {
                            popUpTo(StudyPlanetScreens.MainScreen.name) { inclusive = true }
                        }
                    },
                    navigateBackToDiscoveredPlanetsScreen = {
                        navController.navigate(StudyPlanetScreens.DiscoveredPlanetsScreen.name) {
                            popUpTo(StudyPlanetScreens.DiscoveredPlanetsScreen.name) { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxHeight(),
                    isDiscovering = true,
                    selectedPlanet = selectedPlanetViewModel.selectedPlanet,
                    selectedTimeInMinutes = selectedPlanetViewModel.selectedTimeInMinutes,
                )
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry =
        remember(this) {
            navController.getBackStackEntry(navGraphRoute)
        }
    return hiltViewModel(parentEntry)
}
