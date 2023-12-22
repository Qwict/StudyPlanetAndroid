//[app](../../../index.md)/[com.qwict.studyplanetandroid.common.di](../index.md)/[AppModule](index.md)/[provideRegisterUseCase](provide-register-use-case.md)

# provideRegisterUseCase

[androidJvm]\

@Provides

@Singleton

fun [provideRegisterUseCase](provide-register-use-case.md)(repo: [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md)): [RegisterUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-register-use-case/index.md)

Provides an instance of [RegisterUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-register-use-case/index.md) using the specified [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md).

This function creates and returns an instance of [RegisterUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-register-use-case/index.md) by injecting the required [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md). The [RegisterUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-register-use-case/index.md) is responsible for handling the user registration functionality using the provided repository.

#### Return

An instance of [RegisterUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-register-use-case/index.md) for managing user registration.

#### Parameters

androidJvm

| | |
|---|---|
| repo | The [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md) instance used for handling user registration functionality. |
