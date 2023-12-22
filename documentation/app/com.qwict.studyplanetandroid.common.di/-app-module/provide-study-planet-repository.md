//[app](../../../index.md)/[com.qwict.studyplanetandroid.common.di](../index.md)/[AppModule](index.md)/[provideStudyPlanetRepository](provide-study-planet-repository.md)

# provideStudyPlanetRepository

[androidJvm]\

@Provides

@Singleton

fun [provideStudyPlanetRepository](provide-study-planet-repository.md)(api: [StudyPlanetApi](../../com.qwict.studyplanetandroid.data.remote/-study-planet-api/index.md), planetsRepo: [OfflinePlanetsRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-planets-repository/index.md), usersRepository: [OfflineUsersRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-users-repository/index.md)): [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md)

Provides an instance of [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md) using the specified dependencies.

This function creates and returns an implementation of [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md) by injecting the required dependencies. It uses the provided [StudyPlanetApi](../../com.qwict.studyplanetandroid.data.remote/-study-planet-api/index.md) for making API requests, [OfflinePlanetsRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-planets-repository/index.md) for accessing offline planet data, and [OfflineUsersRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-users-repository/index.md) for managing offline user data.

#### Return

An instance of [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md) for handling StudyPlanet data with the specified dependencies.

#### Parameters

androidJvm

| | |
|---|---|
| api | The [StudyPlanetApi](../../com.qwict.studyplanetandroid.data.remote/-study-planet-api/index.md) instance used for making API requests. |
| planetsRepo | The [OfflinePlanetsRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-planets-repository/index.md) instance for accessing offline planet data. |
| usersRepository | The [OfflineUsersRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-users-repository/index.md) instance for managing offline user data. |
