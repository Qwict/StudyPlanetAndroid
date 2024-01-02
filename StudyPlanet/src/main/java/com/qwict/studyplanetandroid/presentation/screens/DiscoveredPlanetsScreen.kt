package com.qwict.studyplanetandroid.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.qwict.studyplanetandroid.domain.model.Planet
import com.qwict.studyplanetandroid.presentation.components.Loader
import com.qwict.studyplanetandroid.presentation.viewmodels.DiscoverViewModel
import com.qwict.studyplanetandroid.presentation.viewmodels.states.DiscoveredPlanetsState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DiscoveredPlanetsScreen(
    navigateToTimeSelectionScreen: (Planet) -> Unit = {},
    modifier: Modifier = Modifier,
    discoverViewModel: DiscoverViewModel = hiltViewModel(),
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = discoverViewModel.screenState.isRefreshing,
        onRefresh = { discoverViewModel.getDiscoveredPlanetsOnline() },
    )

    LaunchedEffect(discoverViewModel) {
        discoverViewModel.getDiscoveredPlanets()
    }

    when (discoverViewModel.state) {
        is DiscoveredPlanetsState.Loading -> {
            Loader()
        }
        is DiscoveredPlanetsState.Error -> {
            Text(text = "Error")
        }
        is DiscoveredPlanetsState.Success -> {
            val planets by (discoverViewModel.state as DiscoveredPlanetsState.Success).discoveredPlanets.collectAsState()
            Column(
                modifier = Modifier.padding(top = 8.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .pullRefresh(pullRefreshState),
                ) {
                    Column(
                        modifier = Modifier
                            .pullRefresh(pullRefreshState),
                    ) {
                        if (planets.isEmpty()) {
                            Column(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surface)
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.TravelExplore,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(bottom = 16.dp),
                                )
                                Text(
                                    text = "No Discovered Planets",
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                )
                                Text(
                                    text = "discover new planets in the Discover tab",
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                )
                            }
                        }
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            items(
                                planets.size,
                            ) { id ->
                                DiscoveredPlanetCard(modifier, planets[id]) {
                                    navigateToTimeSelectionScreen(
                                        planets[id],
                                    )
                                }
                            }
                        }
                    }

                    PullRefreshIndicator(
                        refreshing = discoverViewModel.screenState.isRefreshing,
                        state = pullRefreshState,
                        modifier = Modifier.align(Alignment.TopCenter),
                    )
                }
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
            .padding(horizontal = 16.dp, vertical = 10.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onMineButtonClicked(planet) },
        ) {
            Row {
                Image(
                    painter = painterResource(planet.smallImageId),
                    contentDescription = planet.name,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Fit,
                )
                Text(
                    text = planet.name,
                    modifier = Modifier.padding(16.dp)
                        .weight(1f),
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.titleLarge,
                )
                // Icon button aligned to the right of the card, centered vertically
                IconButton(
                    onClick = { onMineButtonClicked(planet) },
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForwardIos,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}
