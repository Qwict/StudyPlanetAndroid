package com.qwict.studyplanetandroid.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qwict.studyplanetandroid.data.Planet
import com.qwict.studyplanetandroid.ui.MainViewModel

@Composable
fun TimeSelectionScreen(
    planet: Planet,
    modifier: Modifier = Modifier,
    onStartExploringButtonClicked: (Planet, Long) -> Unit = { planet: Planet, l: Long -> },
    viewModel: MainViewModel,
) {
    Column {
        Text(text = "Select time to explore")
        OutlinedButton(
            onClick = {
                onStartExploringButtonClicked(planet, (30 * 60 * 1000).toLong())
            },
            modifier = Modifier.padding(16.dp),
        ) {
            Text(text = "00:30")
        }

        OutlinedButton(
            onClick = {
                onStartExploringButtonClicked(planet, (60 * 60 * 1000).toLong())
            },
            modifier = Modifier.padding(16.dp),
        ) {
            Text(text = "01:00")
        }

        OutlinedButton(
            onClick = {
                onStartExploringButtonClicked(planet, (120 * 60 * 1000).toLong())
            },
            modifier = Modifier.padding(16.dp),
        ) {
            Text(text = "02:00")
        }
    }
}
