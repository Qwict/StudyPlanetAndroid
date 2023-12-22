package com.qwict.studyplanetandroid.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.qwict.studyplanetandroid.R

@Composable
fun Loader() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(256.dp),
            painter = painterResource(id = R.drawable.study_planet_icon),
            contentDescription = "The application is loading",
        )
        Spacer(modifier = Modifier.size(16.dp))
        CircularProgressIndicator(
            modifier = Modifier.size(54.dp),
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
