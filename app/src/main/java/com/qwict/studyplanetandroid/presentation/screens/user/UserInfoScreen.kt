package com.qwict.studyplanetandroid.presentation.screens.user

import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.common.AuthenticationSingleton
import com.qwict.studyplanetandroid.presentation.components.AuthenticationButton
import com.qwict.studyplanetandroid.presentation.components.LevelExperienceBar
import com.qwict.studyplanetandroid.presentation.viewmodels.states.AuthState

@Composable
fun UserInfoScreen(
    authState: AuthState,
    showSnackbar: (String, SnackbarDuration) -> Unit,
) {
    val buttonText: String

    //            UserPicture(
    //                url = viewModel.user.picture,
    //                description = viewModel.user.name,
    //            )
    UserInfoRow(
        label = stringResource(R.string.email_label),
        value = authState.user.email,
    )
    UserInfoRow(
        label = "username",
        value = authState.user.name,
    )
    LevelExperienceBar(
        level = authState.currentLevel,
        experience = authState.user.experience,
        experienceForNextLevel = authState.experienceForNextLevel,
        experienceProgress = authState.experienceProgress,
    )
    buttonText = stringResource(R.string.log_out_button)
    val onClickAction: () -> Unit = {
        AuthenticationSingleton.logout()
        showSnackbar("Logged out", SnackbarDuration.Short)
//                scope.launch {
//                    authViewModel.state.value.snackBarHostState.showSnackbar("Logged out")
//                }
    }
    AuthenticationButton(
        text = buttonText,
        onClick = onClickAction,
    )
}
