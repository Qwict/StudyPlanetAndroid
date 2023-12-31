package com.qwict.studyplanetandroid.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.floor

@Composable
fun TimeSelectionScreen(
    modifier: Modifier = Modifier,
    onStartActionButtonClicked: () -> Unit = {},
    isDiscovering: Boolean,
    selectedTimeInMinutes: Float,
    setSelectedTimeInMinutes: (Float) -> Unit,
) {
    Log.i("TimeSelectionScreen", "isDiscovering: $isDiscovering")
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            text = "Select the amount of time you want to study",
        )
        if (isDiscovering) {
            Text(
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                text = "The chance of finding a new planet increases the longer you study.",
            )
        } else {
            Text(
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                text = "The exploration of this planet will grant more experience points depending " +
                    "on the time you spend exploring it.",
            )
        }
        SliderAdvancedExample(selectedTimeInMinutes, setSelectedTimeInMinutes)
        OutlinedButton(onClick = onStartActionButtonClicked) {
            Text(text = "Start")
        }
    }
}

@Composable
fun SliderAdvancedExample(selectedTimeInMinutes: Float, setSelectedTimeInMinutes: (Float) -> Unit) {
    Column {
        Slider(
            value = selectedTimeInMinutes,
            onValueChange = { setSelectedTimeInMinutes(it) },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = 6,
            valueRange = 1f..6f,
//            valueRange = 15f..120f,
        )
//        Large text that shows how long the user has selected must be centered
        Text(
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            modifier = Modifier.padding(16.dp),
            text = calculateTime(selectedTimeInMinutes),
        )
    }
}

fun calculateTime(value: Float): String {
    val hours = floor(value / 60)
    val minutes = floor(value % 60)
    // format hours and minutes so that they always have 2 digits
    val hoursString = hours.toInt().toString().padStart(2, '0')
    val minutesString = minutes.toInt().toString().padStart(2, '0')
    return "$hoursString:$minutesString:00"
}
