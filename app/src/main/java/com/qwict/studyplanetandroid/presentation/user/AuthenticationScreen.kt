package com.qwict.studyplanetandroid.presentation.user

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.SnackbarDuration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.common.AuthenticationSingleton
import com.qwict.studyplanetandroid.presentation.components.AuthenticationButton
import com.qwict.studyplanetandroid.presentation.viewmodels.UserViewModel
import com.qwict.studyplanetandroid.presentation.viewmodels.sealed.AuthenticationFormEvent
import com.qwict.studyplanetandroid.presentation.viewmodels.states.AuthState
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationScreen(
    userViewModel: UserViewModel = hiltViewModel(),
    authState: AuthState,
    loginUser: () -> Unit,
    switchIsRegistering: () -> Unit,
    validationEvent: Flow<UserViewModel.ValidationEvent>,
    showSnackbar: (String, SnackbarDuration) -> Unit,
    onEvent: (AuthenticationFormEvent) -> Unit,
) {
//    val name = remember { mutableStateOf(TextFieldValue()) }
//    val email = remember { mutableStateOf(TextFieldValue()) }
//    val password = remember { mutableStateOf(TextFieldValue()) }
//    val confirmPassword = remember { mutableStateOf(TextFieldValue()) }

    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        val toastText = if (authState.registerNewUser) { "Registration Successfully" } else { "Login Successfully" }
        validationEvent.collect { event ->
            when (event) {
                is UserViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context,
                        toastText,
                        Toast.LENGTH_LONG,
                    ).show()
                }
            }
        }
    }

    val buttonText: String
    val onClickAction: () -> Unit

    if (userViewModel.state.registerNewUser) {
        TextField(
            label = { Text(text = "Username") },
            value = authState.username,
            isError = authState.emailError.isNotEmpty(),
            onValueChange = { onEvent(AuthenticationFormEvent.UsernameChanged(it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )
        if (authState.usernameError.isNotEmpty()) {
            Text(
                text = authState.usernameError,
                color = MaterialTheme.colorScheme.error,
            )
        } else {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
    TextField(
        label = { Text(text = "Email") },
        value = userViewModel.state.email,
        isError = authState.emailError.isNotEmpty(),
        onValueChange = { onEvent(AuthenticationFormEvent.EmailChanged(it)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    )
    if (authState.emailError.isNotEmpty()) {
        Text(
            text = authState.emailError,
            color = MaterialTheme.colorScheme.error,
        )
    } else {
        Spacer(modifier = Modifier.height(20.dp))
    }

    Spacer(modifier = Modifier.height(20.dp))
    TextField(
        label = { Text(text = "Password") },
        value = authState.password,
        isError = authState.passwordError.isNotEmpty(),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        onValueChange = { onEvent(AuthenticationFormEvent.PasswordChanged(it)) },
    )
    if (authState.passwordError.isNotEmpty()) {
        Text(
            text = authState.passwordError,
            color = MaterialTheme.colorScheme.error,
        )
    } else {
        Spacer(modifier = Modifier.height(20.dp))
    }
    if (userViewModel.state.registerNewUser) {
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            label = { Text(text = "Confirm Password") },
            value = authState.confirmPassword,
            isError = authState.confirmPasswordError.isNotEmpty(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { onEvent(AuthenticationFormEvent.ConfirmPasswordChanged(it)) },
        )
        if (authState.confirmPasswordError.isNotEmpty()) {
            Text(
                text = authState.confirmPasswordError,
                color = MaterialTheme.colorScheme.error,
            )
        } else {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    if (userViewModel.state.registerNewUser) {
        buttonText = "Register"
        onClickAction = { userViewModel.registerUser() }
    } else {
        buttonText = stringResource(R.string.log_in_button)
        onClickAction = { loginUser() }
    }

    AuthenticationButton(
        text = buttonText,
        onClick = onClickAction,
    )
    if (!AuthenticationSingleton.isUserAuthenticated) {
        OutlinedButton(onClick = { userViewModel.switchIsRegistering() }) {
            if (authState.registerNewUser) {
                Text(text = "Cancel")
            } else {
                Text(text = "Register")
            }
        }
    }
}
