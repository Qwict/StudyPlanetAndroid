package com.qwict.studyplanetandroid.presentation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.StudyPlanetScreens
import com.qwict.studyplanetandroid.presentation.viewmodels.MainViewModel
import com.qwict.studyplanetandroid.presentation.user.UserScreen
import com.qwict.studyplanetandroid.ui.screens.DiscoveredPlanetsScreen
import com.qwict.studyplanetandroid.ui.screens.ExplorerScreen
import com.qwict.studyplanetandroid.ui.screens.MainScreen
import com.qwict.studyplanetandroid.ui.screens.TimeSelectionScreen

@Composable
fun StudyPlanetNavigation(
    innerPadding: PaddingValues,
    navController: NavHostController,
    showSnackbar: (String, SnackbarDuration) -> Unit,
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    NavHost(
        navController = navController,
        startDestination = StudyPlanetScreens.MainScreen.name,
        modifier = androidx.compose.ui.Modifier.padding(innerPadding),
    ) {
//        composable(route = StudyPlanetScreens.StartScreen.name) {
//            AuthenticationScreen(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(dimensionResource(R.dimen.padding_medium)),
//                showSnackbar = showSnackbar,
//            )
//        }
        composable(route = StudyPlanetScreens.MainScreen.name) {
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
            )
        }
        composable(route = StudyPlanetScreens.AuthenticationScreen.name) {
            UserScreen(
                showSnackbar = showSnackbar,
            )
        }
        composable(route = StudyPlanetScreens.DiscoveredPlanetsScreen.name) {
            DiscoveredPlanetsScreen(
                onMineButtonClicked = {
                    mainViewModel.selectedPlanet = it
                    mainViewModel.isDiscovering = false
                    navController.navigate(StudyPlanetScreens.TimeSelectionScreen.name)
                },
                modifier = Modifier.fillMaxHeight(),
            )
        }
        composable(route = StudyPlanetScreens.TimeSelectionScreen.name) {
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
        composable(route = StudyPlanetScreens.PlanetExplorerScreen.name) {
            BackHandler(true) {
                // Or do nothing
                Log.i("Application", "Clicked back")
            }
            ExplorerScreen(
                onCancelStudyButtonClicked = {
                    navController.navigate(StudyPlanetScreens.MainScreen.name)
                },
                modifier = Modifier.fillMaxHeight(),
                isDiscovering = true,
                selectedPlanet = mainViewModel.selectedPlanet,
                selectedTimeInMinutes = mainViewModel.selectedTimeInMinutes,
            )
        }
    }
}
