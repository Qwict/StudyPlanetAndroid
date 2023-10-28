package com.qwict.studyplanetandroid.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.qwict.studyplanetandroid.service.AuthenticationSingleton.isUserAuthenticated
import com.qwict.studyplanetandroid.ui.viewModels.AuthViewModel
import com.qwict.studyplanetandroid.ui.viewModels.UserViewModel

@Composable
fun ExperienceBar(
    userViewModel: UserViewModel = viewModel(factory = UserViewModel.factory),
    authViewModel: AuthViewModel = viewModel<AuthViewModel>(),
) {
    val user by userViewModel.getUser().collectAsState()
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
