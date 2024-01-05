package com.qwict.studyplanetandroid.presentation.screens.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
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
import com.qwict.studyplanetandroid.presentation.components.Loader
import com.qwict.studyplanetandroid.presentation.viewmodels.AuthViewModel
import com.qwict.studyplanetandroid.presentation.viewmodels.sealed.AuthenticationFormEvent
import com.qwict.studyplanetandroid.presentation.viewmodels.states.AuthState
import kotlinx.coroutines.flow.Flow

@Composable
fun LoginScreen(
    authState: AuthState,
    loginUser: () -> Unit,
    navigateToRegisterScreen: () -> Unit,
    validationEvent: Flow<AuthViewModel.ValidationEvent>,
    onEvent: (AuthenticationFormEvent) -> Unit,
    switchPasswordVisibility: () -> Unit,
    clearValidationErrors: () -> Unit,
    windowSize: WindowSizeClass,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = null) {
        validationEvent.collect { event ->
            when (event) {
                is AuthViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context,
                        "Logged in!",
                        Toast.LENGTH_LONG,
                    ).show()
                }
            }
        }
    }

    if (authState.isLoading) {
        Loader()
    } else {
        when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(
                        modifier =
                            Modifier
                                .padding(20.dp)
                                .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.study_planet_icon),
                            contentDescription = "The logo of Study Planet",
                            modifier = Modifier.weight(1f),
                        )
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.weight(2f),
                        ) {
                            Text(
                                textAlign = TextAlign.Center,
                                text = authState.error,
                                style =
                                    TextStyle(
                                        fontFamily = FontFamily.Default,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                        color = MaterialTheme.colorScheme.error,
                                    ),
                            )
                            OutlinedTextField(
                                label = { Text(text = "Email") },
                                value = authState.email,
                                isError = authState.emailError.isNotEmpty(),
                                onValueChange = { onEvent(AuthenticationFormEvent.EmailChanged(it)) },
                                keyboardOptions =
                                    KeyboardOptions(
                                        keyboardType = KeyboardType.Email,
                                        imeAction = ImeAction.Next,
                                    ),
                                singleLine = true,
                            )
                            Text(
                                text = authState.emailError,
                                color = MaterialTheme.colorScheme.error,
                            )
                            OutlinedTextField(
                                label = { Text(text = "Password") },
                                value = authState.password,
                                isError = authState.passwordError.isNotEmpty(),
                                visualTransformation =
                                    if (authState.isPasswordVisible) {
                                        VisualTransformation.None
                                    } else {
                                        PasswordVisualTransformation()
                                    },
                                keyboardOptions =
                                    KeyboardOptions(
                                        keyboardType = KeyboardType.Password,
                                    ),
                                keyboardActions = KeyboardActions(onDone = { loginUser() }),
                                onValueChange = { onEvent(AuthenticationFormEvent.PasswordChanged(it)) },
                                trailingIcon = {
                                    val image =
                                        if (authState.isPasswordVisible) {
                                            Icons.Filled.Visibility
                                        } else {
                                            Icons.Filled.VisibilityOff
                                        }
                                    val description =
                                        if (authState.isPasswordVisible) "Hide password" else "Show password"
                                    IconButton(onClick = { switchPasswordVisibility() }) {
                                        Icon(imageVector = image, description)
                                    }
                                },
                                singleLine = true,
                            )
                            Text(
                                text = authState.passwordError,
                                color = MaterialTheme.colorScheme.error,
                            )

                            Column(
                                modifier = Modifier.fillMaxHeight(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Bottom,
                            ) {
                                Button(
                                    onClick = { loginUser() },
                                    modifier = Modifier.padding(bottom = 4.dp),
                                ) {
                                    Text(text = "Login")
                                }
                                ClickableText(
                                    text = AnnotatedString("Register instead"),
                                    onClick = {
                                        navigateToRegisterScreen()
                                        clearValidationErrors()
                                    },
                                )
                            }
                        }
                    }
                }
            }
            else -> {
                Row(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Column(
                        modifier =
                            Modifier
                                .padding(20.dp)
                                .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Image(
                            modifier = Modifier.weight(3f),
                            painter = painterResource(id = R.drawable.study_planet_icon),
                            contentDescription = "The logo of Study Planet",
                        )
                        Text(
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            text = authState.error,
                            style =
                                TextStyle(
                                    fontFamily = FontFamily.Default,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 25.sp,
                                    color = MaterialTheme.colorScheme.error,
                                ),
                        )
                    }
                    Column(
                        modifier =
                            Modifier
                                .padding(16.dp)
                                .weight(1.5f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        OutlinedTextField(
                            label = { Text(text = "Email") },
                            value = authState.email,
                            isError = authState.emailError.isNotEmpty(),
                            onValueChange = { onEvent(AuthenticationFormEvent.EmailChanged(it)) },
                            keyboardOptions =
                                KeyboardOptions(
                                    keyboardType = KeyboardType.Email,
                                    imeAction = ImeAction.Next,
                                ),
                            singleLine = true,
                        )
                        Text(
                            text = authState.emailError,
                            color = MaterialTheme.colorScheme.error,
                        )
                        OutlinedTextField(
                            label = { Text(text = "Password") },
                            value = authState.password,
                            isError = authState.passwordError.isNotEmpty(),
                            visualTransformation =
                                if (authState.isPasswordVisible) {
                                    VisualTransformation.None
                                } else {
                                    PasswordVisualTransformation()
                                },
                            keyboardOptions =
                                KeyboardOptions(
                                    keyboardType = KeyboardType.Password,
                                ),
                            keyboardActions = KeyboardActions(onDone = { loginUser() }),
                            onValueChange = { onEvent(AuthenticationFormEvent.PasswordChanged(it)) },
                            trailingIcon = {
                                val image =
                                    if (authState.isPasswordVisible) {
                                        Icons.Filled.Visibility
                                    } else {
                                        Icons.Filled.VisibilityOff
                                    }
                                val description =
                                    if (authState.isPasswordVisible) "Hide password" else "Show password"
                                IconButton(onClick = { switchPasswordVisibility() }) {
                                    Icon(imageVector = image, description)
                                }
                            },
                            singleLine = true,
                        )
                        Text(
                            text = authState.passwordError,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(bottom = 4.dp),
                        )
                        Button(
                            onClick = { loginUser() },
                            modifier = Modifier.padding(bottom = 4.dp),
                        ) {
                            Text(text = "Login")
                        }
                        ClickableText(
                            text = AnnotatedString("Register instead"),
                            onClick = {
                                navigateToRegisterScreen()
                                clearValidationErrors()
                            },
                        )
                    }
                }
            }
        }
    }
}
