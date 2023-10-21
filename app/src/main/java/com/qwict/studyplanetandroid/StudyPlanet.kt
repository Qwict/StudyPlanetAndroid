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
import androidx.compose.material3.SnackbarHost
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
import com.qwict.studyplanetandroid.data.Planet
import com.qwict.studyplanetandroid.ui.screens.AuthenticationScreen
import com.qwict.studyplanetandroid.ui.screens.DiscoveredPlanetsScreen
import com.qwict.studyplanetandroid.ui.screens.ExplorerScreen
import com.qwict.studyplanetandroid.ui.screens.MainScreen
import com.qwict.studyplanetandroid.ui.screens.TimeSelectionScreen
import com.qwict.studyplanetandroid.ui.viewModels.MainViewModel

enum class StudyPlanetScreens(@StringRes val title: Int) {
    MainScreen(title = R.string.title_main_screen),
    AuthenticationScreen(title = R.string.title_authentication_screen),
    DiscoveredPlanetsScreen(title = R.string.title_discovered_planets_screen),
    TimeSelectionScreen(title = R.string.title_time_selection_screen),
    PlanetExplorerScreen(title = R.string.title_explorer_screen),
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
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Return",
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = { onAccountButtonClicked() }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "The Account screen",
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
        backStackEntry?.destination?.route ?: StudyPlanetScreens.MainScreen.name,
    )

    // TODO: what is this used for?
//    val scope = rememberCoroutineScope()
//    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            StudyPlanetAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                onAccountButtonClicked = {
                    navController.navigate(StudyPlanetScreens.AuthenticationScreen.name)
                },
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = viewModel.uiState.collectAsState().value.snackBarHostState)
        },

    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = StudyPlanetScreens.MainScreen.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = StudyPlanetScreens.MainScreen.name) {
                MainScreen(
                    onStartExploringButtonClicked = {
                        navController.navigate(StudyPlanetScreens.DiscoveredPlanetsScreen.name)
                    },
                    onDiscoverPlanetsButtonClicked = {
                        viewModel.isDiscovering.value = true
                        navController.navigate(StudyPlanetScreens.TimeSelectionScreen.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                    viewModel = viewModel,
                )
            }
            composable(route = StudyPlanetScreens.AuthenticationScreen.name) {
                AuthenticationScreen(
                    viewModel = viewModel,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                )
            }
            composable(route = StudyPlanetScreens.DiscoveredPlanetsScreen.name) {
//                val context = LocalContext.current
                DiscoveredPlanetsScreen(
                    viewModel = viewModel,
                    onMineButtonClicked = { planet: Planet ->
                        navController.navigate(StudyPlanetScreens.TimeSelectionScreen.name)
                        viewModel.selectedPlanet = planet
                    },
                    modifier = Modifier.fillMaxHeight(),
                )
            }
            composable(route = StudyPlanetScreens.TimeSelectionScreen.name) {
                TimeSelectionScreen(
                    viewModel = viewModel,
                    onStartExploringButtonClicked = { planet: Planet ->
                        navController.navigate(StudyPlanetScreens.PlanetExplorerScreen.name)
                        viewModel.selectedPlanet = planet
                    },
                    modifier = Modifier.fillMaxHeight(),
                    planet = viewModel.selectedPlanet,
                )
            }
            composable(route = StudyPlanetScreens.PlanetExplorerScreen.name) {
                ExplorerScreen(
                    planet = viewModel.selectedPlanet,
                    onCancelMiningButtonClicked = {
                        navController.navigate(StudyPlanetScreens.DiscoveredPlanetsScreen.name)
                    },
                    modifier = Modifier.fillMaxHeight(),
                    viewModel = viewModel,
                    isDiscovering = viewModel.isDiscovering.value,
                )
            }
        }
    }
}
