//[app](../../../index.md)/[com.qwict.studyplanetandroid.presentation.viewmodels](../index.md)/[AuthViewModel](index.md)

# AuthViewModel

[androidJvm]\
class [AuthViewModel](index.md)@Injectconstructor(loginUseCase: [LoginUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-login-use-case/index.md), registerUseCase: [RegisterUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-register-use-case/index.md), validators: [Validators](../../com.qwict.studyplanetandroid.domain.validator/-validators/index.md)) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

ViewModel responsible for handling authentication-related functionality in the application.

The [AuthViewModel](index.md) class is a Hilt-aware ViewModel that manages authentication-related use cases, such as user login and registration, along with validators for input validation.

## Constructors

| | |
|---|---|
| [AuthViewModel](-auth-view-model.md) | [androidJvm]<br>@Inject<br>constructor(loginUseCase: [LoginUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-login-use-case/index.md), registerUseCase: [RegisterUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-register-use-case/index.md), validators: [Validators](../../com.qwict.studyplanetandroid.domain.validator/-validators/index.md)) |

## Types

| Name | Summary |
|---|---|
| [ValidationEvent](-validation-event/index.md) | [androidJvm]<br>sealed class [ValidationEvent](-validation-event/index.md) |

## Properties

| Name | Summary |
|---|---|
| [state](state.md) | [androidJvm]<br>var [state](state.md): [AuthState](../../com.qwict.studyplanetandroid.presentation.viewmodels.states/-auth-state/index.md) |
| [validationEvent](validation-event.md) | [androidJvm]<br>val [validationEvent](validation-event.md): Flow&lt;[AuthViewModel.ValidationEvent](-validation-event/index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../-user-view-model/index.md#264516373%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../-user-view-model/index.md#264516373%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Closeable](https://developer.android.com/reference/kotlin/java/io/Closeable.html)) |
| [clearValidationErrors](clear-validation-errors.md) | [androidJvm]<br>fun [clearValidationErrors](clear-validation-errors.md)() |
| [loginUser](login-user.md) | [androidJvm]<br>fun [loginUser](login-user.md)()<br>Initiates the user login process based on the provided email and password in the current state. |
| [onEvent](on-event.md) | [androidJvm]<br>fun [onEvent](on-event.md)(event: [AuthenticationFormEvent](../../com.qwict.studyplanetandroid.presentation.viewmodels.sealed/-authentication-form-event/index.md))<br>Handles events triggered in the authentication form, updating the state accordingly. |
| [registerUser](register-user.md) | [androidJvm]<br>fun [registerUser](register-user.md)()<br>Initiates the user registration process based on the current state's email, username, and password. |
| [switchPasswordVisibility](switch-password-visibility.md) | [androidJvm]<br>fun [switchPasswordVisibility](switch-password-visibility.md)() |
