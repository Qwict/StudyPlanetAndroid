package com.qwict.studyplanetandroid.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.isUserAuthenticated
import com.qwict.studyplanetandroid.domain.model.User

@Composable
fun ExperienceBar(
    user: User,
) {
    if (isUserAuthenticated) {
        Row {
            LinearProgressIndicator(
                modifier = Modifier
                    .width(60.dp)
                    .height(15.dp),
                progress = user.experience / 10f,
            )
        }
    }
}