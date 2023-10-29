package com.qwict.studyplanetandroid.presentation.user

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.domain.use_case.get_user.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(UserState())
    val state: State<UserState> = _state

    private fun login(email: String, password: String) {
        loginUseCase(email, password).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UserState(user = result.data)
                }
                is Resource.Error -> {
                    _state.value = UserState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = UserState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
