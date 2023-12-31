package com.qwict.studyplanetandroid.presentation.viewmodels.states

import com.qwict.studyplanetandroid.domain.model.User
import kotlinx.coroutines.flow.StateFlow

sealed class MainScreenState {
    data class Success(val user: StateFlow<User>) : MainScreenState()
    data class Error(val exception: String) : MainScreenState()
    data object Loading : MainScreenState()
}
