//[app](../../../index.md)/[com.qwict.studyplanetandroid.presentation.viewmodels](../index.md)/[AuthViewModel](index.md)/[loginUser](login-user.md)

# loginUser

[androidJvm]\
fun [loginUser](login-user.md)()

Initiates the user login process based on the provided email and password in the current state.

This function performs validation on the provided email and password using the specified validators. If validation fails, it updates the state with corresponding error messages and returns. If validation succeeds, it calls the loginUseCase to initiate the login process.

The function observes the login process and updates the state accordingly based on the result:

- 
   If the login is successful ([Resource.Success](../../com.qwict.studyplanetandroid.common/-resource/-success/index.md)), it updates the state with the authenticated user.
- 
   If an error occurs during login ([Resource.Error](../../com.qwict.studyplanetandroid.common/-resource/-error/index.md)), it updates the state with the error message.
- 
   During the loading phase ([Resource.Loading](../../com.qwict.studyplanetandroid.common/-resource/-loading/index.md)), it updates the state to indicate loading.

#### Throws

| | |
|---|---|
| [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) | if the loginUseCase is not provided or initialized. |
