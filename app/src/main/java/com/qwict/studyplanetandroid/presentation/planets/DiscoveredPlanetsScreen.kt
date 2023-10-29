package com.qwict.studyplanetandroid.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.qwict.studyplanetandroid.data.Planet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoveredPlanetsScreen(
    onMineButtonClicked: (Planet) -> Unit = {},
    onDiscoverPlanetsButtonClicked: () -> Unit = {},
    onCancelMiningButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    val userPlanets by userViewModel.getPlanets().collectAsState()

    LaunchedEffect(true) {
        userViewModel.getUserById()
    }

    Scaffold() { values ->
        Column {
            if (userPlanets.isEmpty()) {
                Text(
                    text = "No planets discovered yet",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )

                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Discover")
                }
            } else {
                Text(
                    text = "You have discovered ${userPlanets.size} planets! These planets can now be mined for resources.",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }
        LazyColumn(contentPadding = values) {
            items(userPlanets.size) { id ->
                DiscoveredPlanetCard(modifier, userPlanets[id]) {
                    onMineButtonClicked(
                        userPlanets[id],
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
