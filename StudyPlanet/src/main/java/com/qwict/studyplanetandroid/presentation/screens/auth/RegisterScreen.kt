package com.qwict.studyplanetandroid.presentation.screens.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.presentation.components.AuthenticationButton
import com.qwict.studyplanetandroid.presentation.components.Loader
import com.qwict.studyplanetandroid.presentation.viewmodels.AuthViewModel
import com.qwict.studyplanetandroid.presentation.viewmodels.sealed.AuthenticationFormEvent
import com.qwict.studyplanetandroid.presentation.viewmodels.states.AuthState
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    authState: AuthState,
    registerUser: () -> Unit,
    navigateToLoginScreen: () -> Unit,
    validationEvent: Flow<AuthViewModel.ValidationEvent>,
    onEvent: (AuthenticationFormEvent) -> Unit,
    switchPasswordVisibility: () -> Unit,
    clearValidationErrors: () -> Unit,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        clearValidationErrors()
        validationEvent.collect { event ->
            when (event) {
                is AuthViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context,
                        "Welcome ${authState.username}!",
                        Toast.LENGTH_LONG,
                    ).show()
                }
            }
        }
    }
    if (authState.isLoading) {
        Loader()
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    modifier = Modifier.size(256.dp),
                    painter = painterResource(id = R.drawable.study_planet_icon),
                    contentDescription = "The logo of Study Planet created By Feby",
                )
                Text(
                    textAlign = TextAlign.Center,
                    text = authState.error,
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = MaterialTheme.colorScheme.error,
                    ),
                )

                OutlinedTextField(
                    label = { Text(text = "Username") },
                    value = authState.username,
                    isError = authState.emailError.isNotEmpty(),
                    onValueChange = { onEvent(AuthenticationFormEvent.UsernameChanged(it)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                )
                Text(
                    text = authState.usernameError,
                    color = MaterialTheme.colorScheme.error,
                )

                OutlinedTextField(
                    label = { Text(text = "Email") },
                    value = authState.email,
                    isError = authState.emailError.isNotEmpty(),
                    onValueChange = { onEvent(AuthenticationFormEvent.EmailChanged(it)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next,
                    ),
                )
                Text(
                    text = authState.emailError,
                    color = MaterialTheme.colorScheme.error,
                )

                OutlinedTextField(
                    label = { Text(text = "Password") },
                    value = authState.password,
                    isError = authState.passwordError.isNotEmpty(),
                    visualTransformation = if (authState.isPasswordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next,
                    ),
                    onValueChange = { onEvent(AuthenticationFormEvent.PasswordChanged(it)) },
                    trailingIcon = {
                        val image = if (authState.isPasswordVisible) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        }
                        val description = if (authState.isPasswordVisible) "Hide password" else "Show password"
                        IconButton(onClick = { switchPasswordVisibility() }) {
                            Icon(imageVector = image, description)
                        }
                    },
                )
                Text(
                    text = authState.passwordError,
                    color = MaterialTheme.colorScheme.error,
                )

                OutlinedTextField(
                    label = { Text(text = "Confirm Password") },
                    value = authState.confirmPassword,
                    isError = authState.confirmPasswordError.isNotEmpty(),
                    visualTransformation = if (authState.isPasswordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { onEvent(AuthenticationFormEvent.ConfirmPasswordChanged(it)) },
                    trailingIcon = {
                        val image = if (authState.isPasswordVisible) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        }
                        val description = if (authState.isPasswordVisible) "Hide password" else "Show password"
                        IconButton(onClick = { switchPasswordVisibility() }) {
                            Icon(imageVector = image, description)
                        }
                    },
                )
                Text(
                    text = authState.confirmPasswordError,
                    color = MaterialTheme.colorScheme.error,
                )

                Spacer(modifier = Modifier.height(20.dp))

                AuthenticationButton(
                    text = "Register",
                    onClick = { registerUser() },
                )
                OutlinedButton(onClick = { navigateToLoginScreen() }) {
                    Text(text = "Login")
                }
            }
        }
    }
}
