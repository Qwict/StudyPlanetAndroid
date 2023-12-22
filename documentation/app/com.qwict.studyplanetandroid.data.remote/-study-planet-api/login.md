//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.remote](../index.md)/[StudyPlanetApi](index.md)/[login](login.md)

# login

[androidJvm]\

@POST(value = &quot;v1/users/login&quot;)

abstract suspend fun [login](login.md)(@Bodybody: [LoginDto](../../com.qwict.studyplanetandroid.data.remote.dto/-login-dto/index.md)): [AuthenticatedUserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md)

Attempts to log in a user with the provided [LoginDto](../../com.qwict.studyplanetandroid.data.remote.dto/-login-dto/index.md).

#### Return

The [AuthenticatedUserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md) representing the authenticated user.

#### Parameters

androidJvm

| | |
|---|---|
| body | The [LoginDto](../../com.qwict.studyplanetandroid.data.remote.dto/-login-dto/index.md) containing login credentials. |
