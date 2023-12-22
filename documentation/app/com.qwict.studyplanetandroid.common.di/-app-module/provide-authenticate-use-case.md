//[app](../../../index.md)/[com.qwict.studyplanetandroid.common.di](../index.md)/[AppModule](index.md)/[provideAuthenticateUseCase](provide-authenticate-use-case.md)

# provideAuthenticateUseCase

[androidJvm]\

@Provides

@Singleton

fun [provideAuthenticateUseCase](provide-authenticate-use-case.md)(repo: [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md)): [AuthenticateUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-authenticate-use-case/index.md)

Provides an instance of [AuthenticateUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-authenticate-use-case/index.md) using the specified [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md).

This function creates and returns an instance of [AuthenticateUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-authenticate-use-case/index.md) by injecting the required [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md). The [AuthenticateUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-authenticate-use-case/index.md) is responsible for handling user authentication using the provided repository.

#### Return

An instance of [AuthenticateUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-authenticate-use-case/index.md) for managing user authentication.

#### Parameters

androidJvm

| | |
|---|---|
| repo | The [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md) instance used for handling user authentication functionality. |
