//[app](../../../index.md)/[com.qwict.studyplanetandroid.presentation.viewmodels](../index.md)/[AuthViewModel](index.md)/[onEvent](on-event.md)

# onEvent

[androidJvm]\
fun [onEvent](on-event.md)(event: [AuthenticationFormEvent](../../com.qwict.studyplanetandroid.presentation.viewmodels.sealed/-authentication-form-event/index.md))

Handles events triggered in the authentication form, updating the state accordingly.

This function takes an [AuthenticationFormEvent](../../com.qwict.studyplanetandroid.presentation.viewmodels.sealed/-authentication-form-event/index.md) as input and performs state modifications based on the event type:

- 
   [AuthenticationFormEvent.UsernameChanged](../../com.qwict.studyplanetandroid.presentation.viewmodels.sealed/-authentication-form-event/-username-changed/index.md): Updates the state with the new username.
- 
   [AuthenticationFormEvent.EmailChanged](../../com.qwict.studyplanetandroid.presentation.viewmodels.sealed/-authentication-form-event/-email-changed/index.md): Updates the state with the new email.
- 
   [AuthenticationFormEvent.PasswordChanged](../../com.qwict.studyplanetandroid.presentation.viewmodels.sealed/-authentication-form-event/-password-changed/index.md): Updates the state with the new password.
- 
   [AuthenticationFormEvent.ConfirmPasswordChanged](../../com.qwict.studyplanetandroid.presentation.viewmodels.sealed/-authentication-form-event/-confirm-password-changed/index.md): Updates the state with the new confirm password.
- 
   [AuthenticationFormEvent.RegisterClicked](../../com.qwict.studyplanetandroid.presentation.viewmodels.sealed/-authentication-form-event/-register-clicked/index.md): Invokes the [registerUser](register-user.md) function to initiate user registration.

#### Parameters

androidJvm

| | |
|---|---|
| event | The [AuthenticationFormEvent](../../com.qwict.studyplanetandroid.presentation.viewmodels.sealed/-authentication-form-event/index.md) triggering the state update. |
