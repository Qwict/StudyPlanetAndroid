package com.qwict.studyplanetandroid.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.qwict.studyplanetandroid.R

@Composable
fun DiscoveredPlanetsScreen(
    onEarthMineButtonClicked: () -> Unit = {},
    onCancelButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column() {
        DiscoveredPlanetCard(modifier, "Earth", onEarthMineButtonClicked)
        DiscoveredPlanetCard(modifier, "Mars")
        DiscoveredPlanetCard(modifier, "Europe")
    }

}

@Composable
fun DiscoveredPlanetCard(
    modifier: Modifier = Modifier,
    PlanetName: String = "Earth",
    onEarthMineButtonClicked: () -> Unit = {},
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
                text = PlanetName,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
            )
            OutlinedButton(
                onClick = {onEarthMineButtonClicked()},
                modifier = Modifier.padding(16.dp)

            ) {
                Text(text = "Mine")
            }
        }
    }
}