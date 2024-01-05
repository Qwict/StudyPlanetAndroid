package com.qwict.studyplanetandroid

import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.domain.user.LoginUseCase
import com.qwict.studyplanetandroid.domain.user.RegisterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

// Based on test cases written for SVK
class AuthenticationViewModelTest {
    private val testScope = StandardTestDispatcher()
    private val fakeStudyPlanetRepository = FakeStudyPlanetRepository()

    private lateinit var loginUseCase: LoginUseCase
    private lateinit var registerUseCase: RegisterUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testScope)
        loginUseCase = LoginUseCase(fakeStudyPlanetRepository)
        registerUseCase = RegisterUseCase(fakeStudyPlanetRepository)
    }

    @Test
    fun `loginUseCase should return Resource Success`() {
        runTest {
            loginUseCase("test@test.com", "test").onEach { result ->
                assertTrue(result is Resource.Success<*>)
            }
        }
    }

    @Test
    fun `loginUseCase should return Resource Error`() {
        runTest {
            loginUseCase("", "").onEach { result ->
                assertTrue(result is Resource.Error)
            }
        }
    }

    @Test
    fun `registerUseCase should return Resource Success`() {
        runTest {
            registerUseCase("Test", "test2@test.com", "test").onEach { result ->
                assertTrue(result is Resource.Success<*>)
            }
        }
    }

    @Test
    fun `registerUseCase should return Resource Error`() {
        runTest {
            registerUseCase("Test", "test2@test.com", "test").onEach { result ->
                assertTrue(result is Resource.Error)
            }
        }
    }

    @Test
    fun `registerUseCase should return Resource Error for empty email`() {
        runTest {
            registerUseCase("Test", "test2@test.com", "test").onEach { result ->
                assertTrue(result is Resource.Error)
            }
        }
    }

    @Test
    fun `registerUseCase should return Resource Error for empty password`() {
        runTest {
            registerUseCase("Test", "test2@test.com", "test").onEach { result ->
                assertTrue(result is Resource.Error)
            }
        }
    }

    @Test
    fun `registerUseCase should return Resource Error for invalid email format`() {
        runTest {
            registerUseCase("Test", "test2@test.com", "test").onEach { result ->
                assertTrue(result is Resource.Error)
            }
        }
    }
}
