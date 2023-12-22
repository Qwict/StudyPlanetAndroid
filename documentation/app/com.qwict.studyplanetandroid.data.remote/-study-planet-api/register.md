//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.remote](../index.md)/[StudyPlanetApi](index.md)/[register](register.md)

# register

[androidJvm]\

@POST(value = &quot;v1/users/register&quot;)

abstract suspend fun [register](register.md)(@Bodybody: [RegisterDto](../../com.qwict.studyplanetandroid.data.remote.dto/-register-dto/index.md)): [AuthenticatedUserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md)

Registers a new user with the provided [RegisterDto](../../com.qwict.studyplanetandroid.data.remote.dto/-register-dto/index.md).

#### Return

The [AuthenticatedUserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md) representing the registered and authenticated user.

#### Parameters

androidJvm

| | |
|---|---|
| body | The [RegisterDto](../../com.qwict.studyplanetandroid.data.remote.dto/-register-dto/index.md) containing user registration information. |
