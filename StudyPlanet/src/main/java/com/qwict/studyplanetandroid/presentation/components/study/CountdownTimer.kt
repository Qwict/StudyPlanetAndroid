package com.qwict.studyplanetandroid.presentation.components.study

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomCountDownTimer(
    hours: Int,
    minutes: Int,
    seconds: Int,
) {
    Text(
        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
        modifier = Modifier.padding(16.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = String.format("%02d:%02d:%02d", hours, minutes, seconds),
    )
}
