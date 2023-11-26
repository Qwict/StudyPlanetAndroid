package com.qwict.studyplanetandroid.presentation.components.study

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.qwict.studyplanetandroid.domain.model.Planet

@Composable
fun PlanetDiscoveredDialog(
    planet: Planet,
    navigateHome: () -> Unit,
    navigateToDiscoveredPlanets: () -> Unit,
    hasDiscoveredPlanet: Boolean,
) {
    val title = if (hasDiscoveredPlanet) {
        "Planet discovered!"
    } else {
        "No planet discovered"
    }

    val discoveryInformation = if (hasDiscoveredPlanet) {
        "You have discovered ${planet.name}, " +
            "this planet will now be added to your planet list and is available for mining!"
    } else {
        "You have not discovered a planet, but gained some experience and learned a lot!" +
            "To improve your chances of discovering a planet, try to study for a longer period of time."
    }

    Dialog(onDismissRequest = { navigateHome() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(Icons.Filled.TravelExplore, contentDescription = "Planet discovered Icon")
                Text(
                    text = title,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge,
                )

                if (hasDiscoveredPlanet) {
                    Image(
                        painter = painterResource(planet.imageId),
                        contentDescription = "An image of ${planet.name}",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(160.dp),
                    )
                }
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
                    TextButton(
                        onClick = { navigateHome() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Go home")
                    }
                    if (hasDiscoveredPlanet) {
                        TextButton(
                            onClick = {
                                navigateToDiscoveredPlanets()
                            },
                        ) {
                            Text("Checkout planet")
                        }
                    }
                }
            }
        }
    }
}
//        icon = {
//            Icon(Icons.Filled.TravelExplore, contentDescription = "Planet discovered Icon")
//        },
//        title = {
//            Text(text = title)
//        },
//        text = {
//            Text(text = discoveryInformation)
//        },
//        onDismissRequest = {
//            navigateHome()
//        },
//        confirmButton = {
//            TextButton(
//                onClick = {
//                    navigateHome()
//                },
//            ) {
//                Text("Go home")
//            }
//        },
//        dismissButton = {
//            if (hasDiscoveredPlanet) {
//                TextButton(
//                    onClick = {
//                        navigateToDiscoverdPlanets()
//                    },
//                ) {
//                    Text("Checkout planet")
//                }
//            }
//        },
//    )
// }
