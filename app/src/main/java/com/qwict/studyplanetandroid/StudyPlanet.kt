package com.qwict.studyplanetandroid

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.qwict.studyplanetandroid.dto.HealthDto
import com.qwict.studyplanetandroid.service.HealthService
import com.qwict.studyplanetandroid.ui.AuthenticationScreen
import com.qwict.studyplanetandroid.ui.DiscoveredPlanetsScreen
import com.qwict.studyplanetandroid.ui.MainScreen
import com.qwict.studyplanetandroid.ui.MainViewModel
import com.qwict.studyplanetandroid.ui.PlanetExplorerScreen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class StudyPlanetScreens(@StringRes val title: Int) {
    MainScreen(title = R.string.title_main_screen),
    PlanetExplorerScreen(title = R.string.title_explorer_screen),
    DiscoveredPlanetsScreen(title = R.string.title_discovered_planets_screen),
    AuthenticationScreen(title = R.string.title_authentication_screen),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyPlanetAppBar(
    currentScreen: StudyPlanetScreens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    onAccountButtonClicked: () -> Unit = {},
) {
//    val healthService = HealthService()
//    val health = healthService.getHealth()
    val call = HealthService().healthApi.getHealth()
    var version = ""
    call.enqueue(object : Callback<HealthDto> {
        override fun onResponse(call: Call<HealthDto>, response: Response<HealthDto>) {
            if (response.isSuccessful) {
                version = response.body()?.version.toString()
            } else {
                version = "Server is offline"
            }
        }

        override fun onFailure(call: Call<HealthDto>, t: Throwable) {
            version = "Server had internal error on getting version"
        }
    })

    TopAppBar(
        title = { Text(stringResource(currentScreen.title) + " " + version) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Return"
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = { onAccountButtonClicked() }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "The Account screen"
                )
            }
        },

        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyPlanetApp(
    viewModel: MainViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = StudyPlanetScreens.valueOf(
        backStackEntry?.destination?.route ?: StudyPlanetScreens.MainScreen.name
    )

    Scaffold(
        topBar = {
            StudyPlanetAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                onAccountButtonClicked = {
                    navController.navigate(StudyPlanetScreens.AuthenticationScreen.name)
                }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = StudyPlanetScreens.MainScreen.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = StudyPlanetScreens.MainScreen.name) {
                MainScreen(
                    onStartExploringButtonClicked = {
                        navController.navigate(StudyPlanetScreens.DiscoveredPlanetsScreen.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
            composable(route = StudyPlanetScreens.AuthenticationScreen.name) {
                AuthenticationScreen(
                    viewModel = viewModel,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
            composable(route = StudyPlanetScreens.DiscoveredPlanetsScreen.name) {
//                val context = LocalContext.current
                DiscoveredPlanetsScreen(
                    viewModel = viewModel,
                    onMineButtonClicked = { planet ->
                        navController.navigate(StudyPlanetScreens.PlanetExplorerScreen.name)
                        viewModel.selectedPlanet = planet
                    },
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = StudyPlanetScreens.PlanetExplorerScreen.name) {
                PlanetExplorerScreen(
                    planet = viewModel.selectedPlanet,
                    onCancelMiningButtonClicked = {
                        navController.navigate(StudyPlanetScreens.DiscoveredPlanetsScreen.name)
                    },
                    modifier = Modifier.fillMaxHeight()

                )
            }
        }
    }
}