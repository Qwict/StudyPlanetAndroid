package com.qwict.studyplanetandroid.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.qwict.studyplanetandroid.domain.model.Planet
import com.qwict.studyplanetandroid.presentation.viewmodels.states.UserState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DiscoveredPlanetsScreen(
    navigateToTimeSelectionScreen: (Planet) -> Unit = {},
    getOnlinePlanets: (Boolean) -> Unit,
    getLocalPlanets: () -> Unit,
    userState: UserState,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = userState.isRefreshing,
        onRefresh = {
            scope.launch {
                getOnlinePlanets(true)
                if (userState.refreshError != "") {
                    Toast.makeText(
                        context,
                        "Failed: ${userState.refreshError}",
                        Toast.LENGTH_LONG,
                    ).show()
                }
            }
        },
    )

    LaunchedEffect(true) {
        getLocalPlanets()
    }
    Column {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState),
        ) {
            Column {
                if (userState.planets.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surface)
                            .fillMaxSize()
                            .pullRefresh(pullRefreshState),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "No discovered planets yet!",
                            modifier = Modifier.padding(horizontal = 16.dp),
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        )
                        Text(
                            text = "Start discovering new planets!",
                            modifier = Modifier.padding(horizontal = 16.dp),
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        )
                    }
                } else {
                    Text(
                        text = "You have discovered ${userState.planets.size} planets! These planets can now be mined for resources.",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(userState.planets.size) { id ->
                        DiscoveredPlanetCard(modifier, userState.planets[id]) {
                            navigateToTimeSelectionScreen(
                                userState.planets[id],
                            )
                        }
                    }
                }
            }

            PullRefreshIndicator(
                refreshing = userState.isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
            )
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
