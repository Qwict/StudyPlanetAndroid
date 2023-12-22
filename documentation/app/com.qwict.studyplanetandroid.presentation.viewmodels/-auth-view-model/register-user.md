//[app](../../../index.md)/[com.qwict.studyplanetandroid.presentation.viewmodels](../index.md)/[AuthViewModel](index.md)/[registerUser](register-user.md)

# registerUser

[androidJvm]\
fun [registerUser](register-user.md)()

Initiates the user registration process based on the current state's email, username, and password.

This function performs validation on the provided email, username, and password using the specified validators. If validation fails, it updates the state with corresponding error messages and returns. If validation succeeds, it calls the registerUseCase to initiate the registration process.

The function observes the registration process and updates the state accordingly based on the result:

- 
   If the registration is successful ([Resource.Success](../../com.qwict.studyplanetandroid.common/-resource/-success/index.md)), it updates the state with the authenticated user.
- 
   If an error occurs during registration ([Resource.Error](../../com.qwict.studyplanetandroid.common/-resource/-error/index.md)), it updates the state with the error message.
- 
   During the loading phase ([Resource.Loading](../../com.qwict.studyplanetandroid.common/-resource/-loading/index.md)), it updates the state to indicate loading.

After the validation and registration process, the function sends a [ValidationEvent.Success](-validation-event/-success/index.md) through the validationEventChannel to notify the UI that the validation was successful.
