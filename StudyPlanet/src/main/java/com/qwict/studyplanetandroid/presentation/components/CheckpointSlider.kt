package com.qwict.studyplanetandroid.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable

@Composable
fun CheckpointSlider(selectedTimeInMinutes: Float, setSelectedTimeInMinutes: (Float) -> Unit) {
    Slider(
        value = selectedTimeInMinutes,
        onValueChange = { setSelectedTimeInMinutes(it) },
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.secondary,
            activeTrackColor = MaterialTheme.colorScheme.secondary,
            inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        steps = 6,
//        valueRange = 1f..6f,
        valueRange = 15f..120f,
    )
}
