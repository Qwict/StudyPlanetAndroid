package com.qwict.studyplanetandroid.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExperienceBar(
    experience: Int,
    experienceForNextLevel: Int,
    experienceProgress: Float,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
    ) {
        Text(
            text = "Experience",
            style = MaterialTheme.typography.bodyLarge,
            // Should allign text to the left
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start),
        )
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            progress = experienceProgress,
        )
        Text(
            text = "$experience / $experienceForNextLevel",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start),
        )
    }
}
