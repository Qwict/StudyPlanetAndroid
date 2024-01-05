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
    modifier: Modifier = Modifier,
    experience: Int,
    currentLevelProgress: Int,
    experienceForNextLevel: Int,
    experienceProgress: Float,
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(20.dp),
    ) {
        Text(
            text = "Experience",
            style = MaterialTheme.typography.bodyLarge,
            // Should align text to the left
            modifier =
                modifier
                    .fillMaxWidth()
                    .align(Alignment.Start),
        )
        LinearProgressIndicator(
            modifier =
                modifier
                    .fillMaxWidth()
                    .height(10.dp),
            progress = experienceProgress,
        )
        Text(
            text = "$currentLevelProgress / $experienceForNextLevel (total: ${experience}xp)",
            style = MaterialTheme.typography.bodyMedium,
            modifier =
                modifier
                    .fillMaxWidth()
                    .align(Alignment.Start),
        )
    }
}
