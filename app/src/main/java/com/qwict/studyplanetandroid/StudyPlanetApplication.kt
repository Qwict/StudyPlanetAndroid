package com.qwict.studyplanetandroid

import android.app.Application
import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Rocket
import androidx.compose.material.icons.filled.SatelliteAlt
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.isUserAuthenticated
import com.qwict.studyplanetandroid.data.local.AppContainer
import com.qwict.studyplanetandroid.data.local.AppDataContainer
import com.qwict.studyplanetandroid.presentation.SnackbarAppState
import com.qwict.studyplanetandroid.presentation.StudyPlanetNavigation
import com.qwict.studyplanetandroid.presentation.rememberSnackbarAppState
import com.qwict.studyplanetandroid.presentation.viewmodels.MainViewModel
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

enum class StudyPlanetScreens(@StringRes val title: Int) {
    StartScreen(title = R.string.title_start_screen),
    MainScreen(title = R.string.title_main_screen),
    AuthenticationScreen(title = R.string.title_authentication_screen),
    DiscoveredPlanetsScreen(title = R.string.title_discovered_planets_screen),
    TimeSelectionScreen(title = R.string.title_time_selection_screen),
    PlanetExplorerScreen(title = R.string.title_explorer_screen),
}

@HiltAndroidApp
class StudyPlanetApplication : Application() {
    private lateinit var appScope: CoroutineScope
    private lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
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
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = modifier,
//        navigationIcon = {
//            if (canNavigateBack) {
//                IconButton(onClick = navigateUp) {
//                    Icon(
//                        imageVector = Icons.Filled.ArrowBack,
//                        contentDescription = "Return",
//                    )
//                }
//            }
//        },
        actions = {
//            if (userViewModel.userIsAuthenticated) {
//            TODO: This guy seems to cause a lot of rerenders..
//            ExperienceBar()
//            }

            if (currentScreen.name != StudyPlanetScreens.PlanetExplorerScreen.name) {
                AccountButton(onAccountButtonClicked, currentScreen)
            }
        },

    )
}

@Composable
fun AccountButton(
    onAccountButtonClicked: () -> Unit,
    currentScreen: StudyPlanetScreens,
) {
    if (isUserAuthenticated) {
        IconButton(
            enabled = currentScreen.name != StudyPlanetScreens.AuthenticationScreen.name,
            onClick = { onAccountButtonClicked() },
        ) {
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

@Composable
fun StudyPlanetNavBar(
    currentScreen: StudyPlanetScreens,
    navController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    data class NavbarItem(
        var text: String = "",
        var name: String = "",
        var icon: ImageVector = Icons.Filled.Favorite,
        var contentDescription: String = "",
        var onClick: () -> Unit = {},
    )

    var selectedNavbarItemIndex by remember { mutableStateOf(0) }

    val items = listOf(
        NavbarItem(
            text = "Home",
            name = StudyPlanetScreens.MainScreen.name,
            icon = Icons.Filled.Rocket,
            contentDescription = "Mission Control Center",
            onClick = { navController.navigate(StudyPlanetScreens.MainScreen.name) },
        ),
        NavbarItem(
            text = "Discover",
            name = StudyPlanetScreens.TimeSelectionScreen.name,
            icon = Icons.Filled.SatelliteAlt,
            contentDescription = "Discover a new planet",
            onClick = {
                mainViewModel.isDiscovering = true
                navController.navigate(StudyPlanetScreens.TimeSelectionScreen.name)
            },
        ),
        NavbarItem(
            text = "Explore",
            name = StudyPlanetScreens.DiscoveredPlanetsScreen.name,
            icon = Icons.Filled.TravelExplore,
            contentDescription = "Explore a planet",
            onClick = { navController.navigate(StudyPlanetScreens.DiscoveredPlanetsScreen.name) },
        ),
    )

    NavigationBar {
//        The user should not be allowed to leave the study screen by clicking the navbar
        if (currentScreen.name != StudyPlanetScreens.PlanetExplorerScreen.name) {
            items.forEachIndexed { index, navbarItem ->
                NavigationBarItem(
                    icon = { Icon(navbarItem.icon, contentDescription = navbarItem.contentDescription) },
                    label = { if (currentScreen.name == navbarItem.name) Text(navbarItem.text) },
                    selected = currentScreen.name == navbarItem.name,
                    onClick = {
                        selectedNavbarItemIndex = index
                        navbarItem.onClick()
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyPlanetApp(
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val appState: SnackbarAppState = rememberSnackbarAppState()
    val currentScreen = StudyPlanetScreens.valueOf(
        backStackEntry?.destination?.route ?: StudyPlanetScreens.MainScreen.name,
    )

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
        bottomBar = { StudyPlanetNavBar(currentScreen, navController) },
    ) { innerPadding ->
        StudyPlanetNavigation(
            innerPadding,
            navController = navController,
            showSnackbar = { message, duration ->
                appState.showSnackbar(message = message, duration = duration)
            },
        )
    }
}
