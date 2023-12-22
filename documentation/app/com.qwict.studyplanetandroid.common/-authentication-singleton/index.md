//[app](../../../index.md)/[com.qwict.studyplanetandroid.common](../index.md)/[AuthenticationSingleton](index.md)

# AuthenticationSingleton

[androidJvm]\
@Singleton

object [AuthenticationSingleton](index.md)

Singleton object responsible for managing user authentication state and related operations.

The [AuthenticationSingleton](index.md) object provides properties and methods to track user authentication status, validate user credentials, retrieve the user's remote ID, and handle user logout.

## Properties

| Name | Summary |
|---|---|
| [isUserAuthenticated](is-user-authenticated.md) | [androidJvm]<br>var [isUserAuthenticated](is-user-authenticated.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indicates whether the user is currently authenticated. |

## Functions

| Name | Summary |
|---|---|
| [getRemoteId](get-remote-id.md) | [androidJvm]<br>fun [getRemoteId](get-remote-id.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Retrieves the remote ID of the authenticated user. |
| [logout](logout.md) | [androidJvm]<br>fun [logout](logout.md)()<br>Logs out the user by resetting the authentication status and removing stored credentials. |
| [validateUser](validate-user.md) | [androidJvm]<br>fun [validateUser](validate-user.md)()<br>Validates user authentication status based on the presence and validity of the stored token. If the token is valid, sets [isUserAuthenticated](is-user-authenticated.md) to true; otherwise, logs out the user. |
