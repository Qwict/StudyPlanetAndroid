package com.qwict.studyplanetandroid.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qwict.studyplanetandroid.data.Planet
import com.qwict.studyplanetandroid.ui.viewModels.MainViewModel

@Composable
fun TimeSelectionScreen(
    planet: Planet,
    modifier: Modifier = Modifier,
    onStartActionButtonClicked: () -> Unit = {},
    viewModel: MainViewModel,
) {
    Column {
        Text(text = "Select time to explore")
        OutlinedButton(
            onClick = {
                onStartActionButtonClicked()
//                    (30 * 60 * 1000).toLong()
                viewModel.selectedTime = (1 * 1000)
                viewModel.selectedPlanet = planet
                viewModel.updatedTime = viewModel.selectedTime
            },
            modifier = Modifier.padding(16.dp),
        ) {
            Text(text = "00:30:00")
        }

        OutlinedButton(
            onClick = {
                onStartActionButtonClicked()
//                    (60 * 60 * 1000).toLong()
                viewModel.selectedTime = (5 * 60 * 1000)
                viewModel.selectedPlanet = planet
                viewModel.updatedTime = viewModel.selectedTime
            },
            modifier = Modifier.padding(16.dp),
        ) {
            Text(text = "01:00:00")
        }

        OutlinedButton(
            onClick = {
                onStartActionButtonClicked()
//                    (120 * 60 * 1000).toLong()
                viewModel.selectedTime = (10 * 60 * 1000)
                viewModel.selectedPlanet = planet
                viewModel.updatedTime = viewModel.selectedTime
            },
            modifier = Modifier.padding(16.dp),
        ) {
            Text(text = "02:00:00")
        }
    }
}
