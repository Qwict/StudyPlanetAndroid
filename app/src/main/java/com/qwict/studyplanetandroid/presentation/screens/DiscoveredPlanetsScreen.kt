package com.qwict.studyplanetandroid.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.qwict.studyplanetandroid.domain.model.Planet
import com.qwict.studyplanetandroid.presentation.viewmodels.DiscoveredPlanetsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun DiscoveredPlanetsScreen(
    onMineButtonClicked: (Planet) -> Unit = {},
    onDiscoverPlanetsButtonClicked: () -> Unit = {},
    onCancelMiningButtonClicked: () -> Unit = {},
    userViewModel: DiscoveredPlanetsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val userState = userViewModel.state.value
    val pullRefreshState = rememberPullRefreshState(
        refreshing = userState.isRefreshing,
        onRefresh = {
            scope.launch {
                userViewModel.getOnlinePlanets()
                if (userViewModel.state.value.refreshError != "") {
                    snackbarHostState.showSnackbar(
                        message = userViewModel.state.value.refreshError,
                    )
                }
            }
        },
    )

    LaunchedEffect(true) {
        userViewModel.getLocalPlanets()
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { values ->
        Column {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .pullRefresh(pullRefreshState),
            ) {
                Column() {
                    if (userState.planets.isEmpty()) {
                        Text(
                            text = userState.error,
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.Center,
                        )

                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Discover")
                        }
                    } else {
                        Text(
                            text = "You have discovered ${userState.planets.size} planets! These planets can now be mined for resources.",
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.Center,
                        )
                    }
                    LazyColumn(contentPadding = values) {
                        items(userState.planets.size) { id ->
                            DiscoveredPlanetCard(modifier, userState.planets[id]) {
                                onMineButtonClicked(
                                    userState.planets[id],
                                )
                            }
                        }
                    }
                }

                PullRefreshIndicator(
                    refreshing = userViewModel.state.value.isRefreshing,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter),
                    backgroundColor = if (userViewModel.state.value.isRefreshing) Color.Red else Color.Green,
                )
            }
        }
    }
}

@Composable
fun DiscoveredPlanetCard(
    modifier: Modifier = Modifier,
    planet: Planet,
    onMineButtonClicked: (Planet) -> Unit = {},
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp),
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
        ) {
            Text(
                text = planet.name,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
            )
            OutlinedButton(
                onClick = { onMineButtonClicked(planet) },
                modifier = Modifier.padding(16.dp),

            ) {
                Text(text = "Mine")
            }
        }
    }
}
