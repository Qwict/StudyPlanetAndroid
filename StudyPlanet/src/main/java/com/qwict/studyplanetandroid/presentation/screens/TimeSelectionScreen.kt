package com.qwict.studyplanetandroid.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.qwict.studyplanetandroid.domain.model.Planet
import com.qwict.studyplanetandroid.presentation.components.CheckpointSlider
import kotlin.math.floor

@Composable
fun TimeSelectionScreen(
    modifier: Modifier = Modifier,
    onStartActionButtonClicked: () -> Unit = {},
    selectedPlanet: Planet,
    isDiscovering: Boolean = selectedPlanet.name == "Galaxy",
    selectedTimeInMinutes: Float,
    setSelectedTimeInMinutes: (Float) -> Unit,
    windowSize: WindowSizeClass,
) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Select ${if (isDiscovering) "discover" else "explore"} duration",
                    modifier =
                        Modifier.padding(vertical = 16.dp)
                            .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge,
                )
                Box(
                    propagateMinConstraints = true,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(horizontal = 32.dp)
                            .height(IntrinsicSize.Min),
                ) {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clip(MaterialTheme.shapes.medium)
                                .background(MaterialTheme.colorScheme.tertiaryContainer),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            painter = painterResource(selectedPlanet.imageId),
                            contentDescription = selectedPlanet.name,
                            modifier =
                                Modifier
                                    .aspectRatio(1f)
                                    .fillMaxWidth(),
                            contentScale = ContentScale.Fit,
                        )

                        CheckpointSliderBlock(
                            modifier = Modifier.padding(top = 8.dp),
                            selectedTimeInMinutes = selectedTimeInMinutes,
                            setSelectedTimeInMinutes = setSelectedTimeInMinutes,
                        )
                    }
                }
                Button(
                    onClick = onStartActionButtonClicked,
                    modifier =
                        Modifier
                            .padding(16.dp),
                ) {
                    Text(text = "Start")
                }
            }
        }
        else -> {
            Row(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(vertical = 4.dp, horizontal = 32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Column(
                    modifier =
                        modifier
                            .padding(horizontal = 16.dp)
                            .weight(1f),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        text = "Select ${if (isDiscovering) "discover" else "explore"} duration",
                    )
                    if (isDiscovering) {
                        Text(
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            text = "The chance of finding a new planet increases the longer you study.",
                        )
                    } else {
                        Text(
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            text =
                                "More time spent studying will increase the amount of experience you gain.",
                        )
                    }
                }
                Column(
                    modifier =
                        Modifier
                            .fillMaxHeight()
                            .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    CheckpointSliderBlock(
                        modifier = Modifier.weight(1f),
                        selectedTimeInMinutes = selectedTimeInMinutes,
                        setSelectedTimeInMinutes = setSelectedTimeInMinutes,
                    )
                    Button(
                        onClick = onStartActionButtonClicked,
                        modifier =
                            Modifier
                                .padding(16.dp),
                    ) {
                        Text(text = "Start")
                    }
                }
            }
        }
    }
}

@Composable
fun CheckpointSliderBlock(
    modifier: Modifier = Modifier,
    selectedTimeInMinutes: Float,
    setSelectedTimeInMinutes: (Float) -> Unit,
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(top = 8.dp),
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            text = calculateTime(selectedTimeInMinutes),
        )
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            text = "Estimated experience gained: ${floor(selectedTimeInMinutes).toInt()}xp",
        )
        CheckpointSlider(selectedTimeInMinutes, setSelectedTimeInMinutes)
    }
}

fun calculateTime(value: Float): String {
    val hours = floor(value / 60)
    val minutes = floor(value % 60)
    val hoursString = hours.toInt().toString().padStart(2, '0')
    val minutesString = minutes.toInt().toString().padStart(2, '0')
    return "$hoursString:$minutesString:00"
}
