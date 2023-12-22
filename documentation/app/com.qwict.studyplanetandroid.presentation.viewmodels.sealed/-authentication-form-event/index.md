//[app](../../../index.md)/[com.qwict.studyplanetandroid.presentation.viewmodels.sealed](../index.md)/[AuthenticationFormEvent](index.md)

# AuthenticationFormEvent

sealed class [AuthenticationFormEvent](index.md)

#### Inheritors

| |
|---|
| [UsernameChanged](-username-changed/index.md) |
| [EmailChanged](-email-changed/index.md) |
| [PasswordChanged](-password-changed/index.md) |
| [ConfirmPasswordChanged](-confirm-password-changed/index.md) |
| [RegisterClicked](-register-clicked/index.md) |

## Types

| Name | Summary |
|---|---|
| [ConfirmPasswordChanged](-confirm-password-changed/index.md) | [androidJvm]<br>data class [ConfirmPasswordChanged](-confirm-password-changed/index.md)(val confirmPassword: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [AuthenticationFormEvent](index.md) |
| [EmailChanged](-email-changed/index.md) | [androidJvm]<br>data class [EmailChanged](-email-changed/index.md)(val email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [AuthenticationFormEvent](index.md) |
| [PasswordChanged](-password-changed/index.md) | [androidJvm]<br>data class [PasswordChanged](-password-changed/index.md)(val password: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [AuthenticationFormEvent](index.md) |
| [RegisterClicked](-register-clicked/index.md) | [androidJvm]<br>data object [RegisterClicked](-register-clicked/index.md) : [AuthenticationFormEvent](index.md) |
| [UsernameChanged](-username-changed/index.md) | [androidJvm]<br>data class [UsernameChanged](-username-changed/index.md)(val username: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [AuthenticationFormEvent](index.md) |
