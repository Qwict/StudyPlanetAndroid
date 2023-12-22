//[app](../../../index.md)/[com.qwict.studyplanetandroid.common.di](../index.md)/[AppModule](index.md)/[provideGetLocalPlanetsUseCase](provide-get-local-planets-use-case.md)

# provideGetLocalPlanetsUseCase

[androidJvm]\

@Provides

@Singleton

fun [provideGetLocalPlanetsUseCase](provide-get-local-planets-use-case.md)(repo: [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md)): [GetLocalPlanetsUseCase](../../com.qwict.studyplanetandroid.domain.use_case.planets/-get-local-planets-use-case/index.md)

Provides an instance of [GetLocalPlanetsUseCase](../../com.qwict.studyplanetandroid.domain.use_case.planets/-get-local-planets-use-case/index.md) using the specified [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md).

This function creates and returns an instance of [GetLocalPlanetsUseCase](../../com.qwict.studyplanetandroid.domain.use_case.planets/-get-local-planets-use-case/index.md) by injecting the required [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md). The [GetLocalPlanetsUseCase](../../com.qwict.studyplanetandroid.domain.use_case.planets/-get-local-planets-use-case/index.md) is responsible for fetching local planet data using the provided repository.

#### Return

An instance of [GetLocalPlanetsUseCase](../../com.qwict.studyplanetandroid.domain.use_case.planets/-get-local-planets-use-case/index.md) for fetching local planet data.

#### Parameters

androidJvm

| | |
|---|---|
| repo | The [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md) instance used for retrieving local planet data. |
