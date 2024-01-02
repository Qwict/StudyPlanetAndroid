package com.qwict.studyplanetandroid.presentation.components.study

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.qwict.studyplanetandroid.domain.model.Planet

@Composable
fun PlanetDiscoveredDialog(
    planet: Planet,
    navigateHome: () -> Unit,
    navigateToDiscoveredPlanets: () -> Unit,
    hasDiscoveredPlanet: Boolean,
    experience: Int,
) {
    val title = if (hasDiscoveredPlanet) {
        "Planet discovered!"
    } else {
        "No planet discovered"
    }

    val discoveryInformation = if (hasDiscoveredPlanet) {
        "You have discovered ${planet.name}, and gained $experience xp!"
    } else {
        "No planet discovered, but gained $experience xp" +
            "To improve your chances of discovering a planet, try to study for a longer period of time."
    }

    Dialog(onDismissRequest = { navigateHome() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium,
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (hasDiscoveredPlanet) {
                    Image(
                        painter = painterResource(planet.imageId),
                        contentDescription = "An image of ${planet.name}",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Fit,
                    )
                }
                Text(
                    text = title,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineLarge,
                )

                Text(
                    text = discoveryInformation,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(
                        onClick = { navigateHome() },
                        modifier = Modifier.padding(16.dp),
                    ) {
                        Text("Go home")
                    }
                    if (hasDiscoveredPlanet) {
                        Button(
                            onClick = {
                                navigateToDiscoveredPlanets()
                            },
                            modifier = Modifier.padding(16.dp),
                        ) {
                            Text("Checkout planet")
                        }
                    }
                }
            }
        }
    }
}
