package com.qwict.studyplanetandroid.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.qwict.studyplanetandroid.data.Planet

@Composable
fun DiscoveredPlanetsScreen(
    viewModel: MainViewModel,
    onMineButtonClicked: (Planet) -> Unit = {},
    onCancelMiningButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column() {
        Text(
            text = "You have discovered ${viewModel.discoveredPlanets.size} " +
                    "planets! These planets can now be mined for resources.",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
        )
        viewModel.discoveredPlanets.forEach { planet ->
            planet.name?.let { DiscoveredPlanetCard(modifier, planet, { onMineButtonClicked(planet) }) }
        }
    }



}

@Composable
fun DiscoveredPlanetCard(
    modifier: Modifier = Modifier,
    planet: Planet,
    onMineButtonClicked: (Planet) -> Unit = {},
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
    ) {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
//                TODO: is this the right way to put this? Will throw a nullpointer exception if the planet name is null
                text = planet.name!!,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
            )
            OutlinedButton(
                onClick = {onMineButtonClicked(planet)},
                modifier = Modifier.padding(16.dp)

            ) {
                Text(text = "Mine")
            }
        }
    }
}