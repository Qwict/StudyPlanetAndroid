//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.repository](../index.md)/[StudyPlanetRepositoryImpl](index.md)/[register](register.md)

# register

[androidJvm]\
open suspend override fun [register](register.md)(body: [RegisterDto](../../com.qwict.studyplanetandroid.data.remote.dto/-register-dto/index.md)): [AuthenticatedUserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md)

Registers a new user remotely and performs local database operations to store the user and associated planets.

#### Return

The [AuthenticatedUserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md) representing the registered and authenticated user.

#### Parameters

androidJvm

| | |
|---|---|
| body | The [RegisterDto](../../com.qwict.studyplanetandroid.data.remote.dto/-register-dto/index.md) containing user registration information. |
