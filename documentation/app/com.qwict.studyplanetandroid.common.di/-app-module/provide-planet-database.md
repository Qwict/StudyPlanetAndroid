//[app](../../../index.md)/[com.qwict.studyplanetandroid.common.di](../index.md)/[AppModule](index.md)/[providePlanetDatabase](provide-planet-database.md)

# providePlanetDatabase

[androidJvm]\

@Provides

@Singleton

fun [providePlanetDatabase](provide-planet-database.md)(): [OfflinePlanetsRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-planets-repository/index.md)

Provides an instance of [OfflinePlanetsRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-planets-repository/index.md) backed by the StudyPlanetDatabase.

This function creates and returns an implementation of [OfflinePlanetsRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-planets-repository/index.md) that utilizes the StudyPlanetDatabase. It initializes the database with the application context and a CoroutineScope, and retrieves the PlanetDao for data access. The returned [OfflinePlanetsRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-planets-repository/index.md) instance is configured as a singleton.

#### Return

An instance of [OfflinePlanetsRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-planets-repository/index.md) using the StudyPlanetDatabase.
