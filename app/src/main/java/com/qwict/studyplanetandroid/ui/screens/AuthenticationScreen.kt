package com.qwict.studyplanetandroid.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.ui.viewModels.AppViewModelProvider
import com.qwict.studyplanetandroid.ui.viewModels.DataViewModel
import com.qwict.studyplanetandroid.ui.viewModels.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun AuthenticationScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    Column() {
        AuthenticationView(modifier, viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationView(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(factory = AppViewModelProvider.Factory),
    dataViewModel: DataViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val scope = rememberCoroutineScope()
    val email = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val confirmPassword = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val title = if (viewModel.userIsAuthenticated.value) {
            stringResource(R.string.logged_in_title)
        } else {
            if (viewModel.appJustLaunched.value) {
                stringResource(R.string.initial_title)
            } else {
                stringResource(R.string.logged_out_title)
            }
        }
        Title(
            text = title,
        )
//        Text(text = )

        if (viewModel.userIsAuthenticated.value) {
            UserInfoRow(
                label = stringResource(R.string.email_label),
                value = viewModel.decodedUser.email,
            )
            UserInfoRow(
                label = "Experience",
//                value = viewModel.user.email,
                value = dataViewModel.getUserById(viewModel.decodedUser.id).experience.toString(),
            )
            //            UserPicture(
            //                url = viewModel.user.picture,
            //                description = viewModel.user.name,
            //            )
        } else {
            TextField(
                label = { Text(text = "Email") },
                value = email.value,
                onValueChange = { email.value = it },
            )

            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text(text = "Password") },
                value = password.value,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { password.value = it },
            )
            if (viewModel.registerNewUser.value) {
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    label = { Text(text = "Confirm Password") },
                    value = confirmPassword.value,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { confirmPassword.value = it },
                )
            }
        }

        val buttonText: String
        val onClickAction: () -> Unit
        if (viewModel.userIsAuthenticated.value) {
            buttonText = stringResource(R.string.log_out_button)
            onClickAction = {
                viewModel.logout()
                scope.launch {
                    viewModel.uiState.value.snackBarHostState.showSnackbar("Logged out")
                }
                email.value = TextFieldValue()
                password.value = TextFieldValue()
            }
        } else if (viewModel.registerNewUser.value) {
            buttonText = "Register"

            onClickAction = {
                if (confirmPassword.value.equals(password.value)) {
                    if (viewModel.register(email, password)) {
                        scope.launch {
                            viewModel.uiState.value.snackBarHostState.showSnackbar(
                                message = "Welcome ${viewModel.decodedUser.email}!",
                                withDismissAction = true,
                            )
                        }
                    } else {
                        scope.launch {
                            viewModel.uiState.value.snackBarHostState.showSnackbar(
                                message = "Failed to register",
                                withDismissAction = true,
                            )
                        }
                    }
                    viewModel.registerNewUser.value = false
                } else {
                    Log.i(
                        "AuthenticationScreen",
                        "Passwords do not match " +
                            "${confirmPassword.value.text} ${password.value.text}",
                    )
                    scope.launch {
                        viewModel.uiState.value.snackBarHostState.showSnackbar(
                            message = "Passwords do not match",
                            withDismissAction = true,
                        )
                    }
                }
            }
        } else {
            buttonText = stringResource(R.string.log_in_button)
            onClickAction = {
                if (!viewModel.login(email, password)) {
                    scope.launch {
                        viewModel.uiState.value.snackBarHostState.showSnackbar(
                            message = "Failed to login",
                            withDismissAction = true,
                        )
                    }
                }
            }
        }
        LogButton(
            text = buttonText,
            onClick = onClickAction,
        )
        if (!viewModel.userIsAuthenticated.value) {
            OutlinedButton(onClick = {
                viewModel.registerNewUser.value = !viewModel.registerNewUser.value
            }) {
                if (viewModel.registerNewUser.value) {
                    Text(text = "Cancel")
                } else {
                    Text(text = "Register")
                }
            }
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

@Composable
fun LogButton(
    text: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { onClick() },
            modifier = Modifier
                .width(200.dp)
                .height(50.dp),
        ) {
            Text(
                text = text,
                fontSize = 20.sp,
            )
        }
    }
}
