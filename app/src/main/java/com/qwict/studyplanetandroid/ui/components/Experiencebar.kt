package com.qwict.studyplanetandroid.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qwict.studyplanetandroid.ui.viewModels.MainViewModel

@Composable
fun ExperienceBar(viewModel: MainViewModel) {
    Row {
        LinearProgressIndicator(
            modifier = Modifier
                .width(60.dp)
                .height(15.dp),
            progress = viewModel.user.experience.value / 10f,
        )
    }
}
