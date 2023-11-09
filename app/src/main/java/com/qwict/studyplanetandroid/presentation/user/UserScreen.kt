package com.qwict.studyplanetandroid.presentation.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.SnackbarDuration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.isUserAuthenticated
import com.qwict.studyplanetandroid.presentation.viewmodels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    showSnackbar: (String, SnackbarDuration) -> Unit,
    userViewModel: UserViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    val authState = userViewModel.state

    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (authState.isLoading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth().padding(0.dp),
            )
        }

        val title = if (isUserAuthenticated) {
            stringResource(R.string.logged_in_title)
        } else {
            if (authState.appJustLaunched) {
                stringResource(R.string.initial_title)
            } else {
                stringResource(R.string.logged_out_title)
            }
        }
        Title(
            text = title,
        )
        Text(
            text = authState.error,
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.error,
            ),
        )
        Spacer(modifier = Modifier.height(20.dp))

        if (isUserAuthenticated) {
            UserInfoScreen(
                authState,
                showSnackbar = showSnackbar,
            )
        } else {
            AuthenticationScreen(
                authState = authState,
                loginUser = { userViewModel.loginUser() },
                switchIsRegistering = { userViewModel.switchIsRegistering() },
                showSnackbar = showSnackbar,
                validationEvent = userViewModel.validationEvent,
                onEvent = { userViewModel.onEvent(it) },
            )
        }
    }
}

@Composable
fun UserInfoRow(
    label: String,
    value: String,
) {
    Row {
        Text(
            text = label,
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            ),
        )
        Spacer(
            modifier = Modifier.width(10.dp),
        )
        Text(
            text = value,
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontSize = 20.sp,
            ),
        )
    }
}

@Composable
fun Title(
    text: String,
) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
        ),
    )
}
