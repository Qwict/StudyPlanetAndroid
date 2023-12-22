//[app](../../../index.md)/[com.qwict.studyplanetandroid.common.di](../index.md)/[AppModule](index.md)/[provideLoginUseCase](provide-login-use-case.md)

# provideLoginUseCase

[androidJvm]\

@Provides

@Singleton

fun [provideLoginUseCase](provide-login-use-case.md)(repo: [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md)): [LoginUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-login-use-case/index.md)

Provides an instance of [LoginUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-login-use-case/index.md) using the specified [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md).

This function creates and returns an instance of [LoginUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-login-use-case/index.md) by injecting the required [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md). The [LoginUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-login-use-case/index.md) is responsible for handling the login functionality using the provided repository.

#### Return

An instance of [LoginUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-login-use-case/index.md) for managing user login.

#### Parameters

androidJvm

| | |
|---|---|
| repo | The [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md) instance used for handling login functionality. |
