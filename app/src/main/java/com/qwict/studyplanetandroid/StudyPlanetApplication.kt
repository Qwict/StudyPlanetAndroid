package com.qwict.studyplanetandroid

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Login
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.qwict.studyplanetandroid.data.AppContainer
import com.qwict.studyplanetandroid.data.AppDataContainer
import com.qwict.studyplanetandroid.ui.screens.AuthenticationScreen
import com.qwict.studyplanetandroid.ui.screens.DiscoveredPlanetsScreen
import com.qwict.studyplanetandroid.ui.screens.ExplorerScreen
import com.qwict.studyplanetandroid.ui.screens.MainScreen
import com.qwict.studyplanetandroid.ui.screens.TimeSelectionScreen
import com.qwict.studyplanetandroid.ui.viewModels.AppViewModelProvider
import com.qwict.studyplanetandroid.ui.viewModels.DataViewModel
import com.qwict.studyplanetandroid.ui.viewModels.MainViewModel
import com.qwict.studyplanetandroid.ui.viewModels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

enum class StudyPlanetScreens(@StringRes val title: Int) {
    MainScreen(title = R.string.title_main_screen),
    AuthenticationScreen(title = R.string.title_authentication_screen),
    DiscoveredPlanetsScreen(title = R.string.title_discovered_planets_screen),
    TimeSelectionScreen(title = R.string.title_time_selection_screen),
    PlanetExplorerScreen(title = R.string.title_explorer_screen),
}

// private const val SETTINGS_PREFERENCE_NAME = "settings_preferences"
// private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
//    name = SETTINGS_PREFERENCE_NAME
// )

class StudyPlanetApplication : Application() {
//    lateinit var userSettings: UserSettings
    lateinit var container: AppContainer
    private lateinit var appScope: CoroutineScope
    override fun onCreate() {
        super.onCreate()
//        userSettings = UserSettings(dataStore)
        appScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        container = AppDataContainer(this, appScope)
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyPlanetAppBar(
    currentScreen: StudyPlanetScreens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    onAccountButtonClicked: () -> Unit = {},
    viewModel: MainViewModel = viewModel(factory = AppViewModelProvider.Factory),
    userViewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory),
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
//            if (userViewModel.userIsAuthenticated) {
//            TODO: This guy seems to cause a lot of rerenders..
//            ExperienceBar()
//            }
            Text(text = "user is authenticated: ${userViewModel.userIsAuthenticated}")
            AccountButton(onAccountButtonClicked)
        },

    )
}

@Composable
fun AccountButton(
    onAccountButtonClicked: () -> Unit,
    userViewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    Log.d("AccountButton", "AccountButton: ${userViewModel.userIsAuthenticated}")
    if (userViewModel.userIsAuthenticated) {
        IconButton(onClick = { onAccountButtonClicked() }) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Your account screen",
            )
        }
    } else {
        IconButton(onClick = { onAccountButtonClicked() }) {
            Icon(
                imageVector = Icons.Filled.Login,
                contentDescription = "The login screen",
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyPlanetNavBar() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Discover", "Explore")

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Favorite, contentDescription = item) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyPlanetApp(
    viewModel: MainViewModel = viewModel(factory = AppViewModelProvider.Factory),
    dataViewModel: DataViewModel = viewModel(factory = AppViewModelProvider.Factory),
    userViewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory),
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
        bottomBar = {
            StudyPlanetNavBar()
        },
        snackbarHost = {
            SnackbarHost(hostState = viewModel.uiState.collectAsState().value.snackBarHostState)
        },

    ) { innerPadding ->
//        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = StudyPlanetScreens.MainScreen.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = StudyPlanetScreens.MainScreen.name) {
                MainScreen(
                    onStartExploringButtonClicked = {
                        navController.navigate(
                            StudyPlanetScreens.DiscoveredPlanetsScreen.name,
                        )
                    },
                    onDiscoverPlanetsButtonClicked = {
                        userViewModel.isDiscovering.value = true
                        navController.navigate(StudyPlanetScreens.TimeSelectionScreen.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                )
            }
            composable(route = StudyPlanetScreens.AuthenticationScreen.name) {
                AuthenticationScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                )
            }
            composable(route = StudyPlanetScreens.DiscoveredPlanetsScreen.name) {
//                val context = LocalContext.current
                DiscoveredPlanetsScreen(
                    onMineButtonClicked = {
                        navController.navigate(StudyPlanetScreens.TimeSelectionScreen.name)
//                        dataViewModel.uiState.value.selectedPlanet = PlanetEntity()
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
                )
            }
            composable(route = StudyPlanetScreens.PlanetExplorerScreen.name) {
                ExplorerScreen(
                    onCancelMiningButtonClicked = {
                        navController.navigate(StudyPlanetScreens.DiscoveredPlanetsScreen.name)
                    },
                    modifier = Modifier.fillMaxHeight(),
                    isDiscovering = userViewModel.isDiscovering.value,
                    planet = userViewModel.selectedPlanet,
                )
            }
        }
    }
}
