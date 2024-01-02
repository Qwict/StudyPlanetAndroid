package com.qwict.studyplanetandroid.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qwict.studyplanetandroid.presentation.components.CheckpointSlider
import kotlin.math.floor

@Composable
fun TimeSelectionScreen(
    modifier: Modifier = Modifier,
    onStartActionButtonClicked: () -> Unit = {},
    isDiscovering: Boolean,
    selectedTimeInMinutes: Float,
    setSelectedTimeInMinutes: (Float) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
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
                text = "The exploration of this planet will grant more experience points depending " +
                    "on the time you spend exploring it.",
            )
        }

        Column(
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CheckpointSlider(selectedTimeInMinutes, setSelectedTimeInMinutes)
            Text(
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                modifier = Modifier.padding(16.dp),
                text = calculateTime(selectedTimeInMinutes),
            )
            Button(onClick = onStartActionButtonClicked) {
                Text(text = "Start")
            }
        }
    }
}

fun calculateTime(value: Float): String {
    val hours = floor(value / 60)
    val minutes = floor(value % 60)
    val hoursString = hours.toInt().toString().padStart(2, '0')
    val minutesString = minutes.toInt().toString().padStart(2, '0')
    return "$hoursString:$minutesString:00"
}
