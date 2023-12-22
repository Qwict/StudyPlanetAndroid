//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.repository](../index.md)/[StudyPlanetRepositoryImpl](index.md)/[login](login.md)

# login

[androidJvm]\
open suspend override fun [login](login.md)(body: [LoginDto](../../com.qwict.studyplanetandroid.data.remote.dto/-login-dto/index.md)): [AuthenticatedUserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md)

Logs in a user remotely and performs local database operations to store the user and associated planets.

#### Return

The [AuthenticatedUserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md) representing the authenticated user.

#### Parameters

androidJvm

| | |
|---|---|
| body | The [LoginDto](../../com.qwict.studyplanetandroid.data.remote.dto/-login-dto/index.md) containing login credentials. |
