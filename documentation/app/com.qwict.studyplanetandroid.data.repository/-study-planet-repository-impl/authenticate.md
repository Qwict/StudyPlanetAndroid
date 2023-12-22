//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.repository](../index.md)/[StudyPlanetRepositoryImpl](index.md)/[authenticate](authenticate.md)

# authenticate

[androidJvm]\
open suspend override fun [authenticate](authenticate.md)(token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [AuthenticatedUserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md)

Authenticates the current user remotely and performs local database operations to store the user and associated planets.

#### Return

The [AuthenticatedUserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md) representing the authenticated user.

#### Parameters

androidJvm

| | |
|---|---|
| token | The authentication token. |
