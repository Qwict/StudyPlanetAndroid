//[app](../../../index.md)/[com.qwict.studyplanetandroid.common.di](../index.md)/[AppModule](index.md)/[provideGetOnlinePlanetsUseCase](provide-get-online-planets-use-case.md)

# provideGetOnlinePlanetsUseCase

[androidJvm]\

@Provides

@Singleton

fun [provideGetOnlinePlanetsUseCase](provide-get-online-planets-use-case.md)(repo: [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md)): [GetOnlinePlanetsUseCase](../../com.qwict.studyplanetandroid.domain.use_case.planets/-get-online-planets-use-case/index.md)

Provides an instance of [GetOnlinePlanetsUseCase](../../com.qwict.studyplanetandroid.domain.use_case.planets/-get-online-planets-use-case/index.md) using the specified [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md).

This function creates and returns an instance of [GetOnlinePlanetsUseCase](../../com.qwict.studyplanetandroid.domain.use_case.planets/-get-online-planets-use-case/index.md) by injecting the required [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md). The [GetOnlinePlanetsUseCase](../../com.qwict.studyplanetandroid.domain.use_case.planets/-get-online-planets-use-case/index.md) is responsible for fetching online planet data using the provided repository.

#### Return

An instance of [GetOnlinePlanetsUseCase](../../com.qwict.studyplanetandroid.domain.use_case.planets/-get-online-planets-use-case/index.md) for fetching online planet data.

#### Parameters

androidJvm

| | |
|---|---|
| repo | The [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md) instance used for retrieving online planet data. |
