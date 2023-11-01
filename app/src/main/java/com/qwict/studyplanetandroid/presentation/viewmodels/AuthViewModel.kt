package com.qwict.studyplanetandroid.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.domain.use_case.get_user.AuthenticateUseCase
import com.qwict.studyplanetandroid.domain.use_case.get_user.LoginUseCase
import com.qwict.studyplanetandroid.presentation.viewmodels.states.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val authenticateUseCase: AuthenticateUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state

    init {
        getUserWithToken()
    }

    fun getUserWithToken() {
        Log.i("AuthViewModel", "getUserWithToken: ")
        authenticateUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AuthState(user = result.data!!)
                }

                is Resource.Error -> {
                    _state.value =
                        AuthState(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _state.value = AuthState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loginUser(email: String, password: String) {
        Log.i("AuthViewModel", "loginUser: $email, $password")
        loginUseCase(email, password).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AuthState(user = result.data!!)
                }

                is Resource.Error -> {
                    _state.value =
                        AuthState(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _state.value = AuthState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
