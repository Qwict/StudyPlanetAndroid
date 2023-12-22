//[app](../../index.md)/[com.qwict.studyplanetandroid.data.repository](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [StudyPlanetRepository](-study-planet-repository/index.md) | [androidJvm]<br>interface [StudyPlanetRepository](-study-planet-repository/index.md) |
| [StudyPlanetRepositoryImpl](-study-planet-repository-impl/index.md) | [androidJvm]<br>class [StudyPlanetRepositoryImpl](-study-planet-repository-impl/index.md)@Injectconstructor(api: [StudyPlanetApi](../com.qwict.studyplanetandroid.data.remote/-study-planet-api/index.md), userDatabase: [OfflineUsersRepository](../com.qwict.studyplanetandroid.data.local.database/-offline-users-repository/index.md), planetDatabase: [OfflinePlanetsRepository](../com.qwict.studyplanetandroid.data.local.database/-offline-planets-repository/index.md)) : [StudyPlanetRepository](-study-planet-repository/index.md)<br>Implementation of the [StudyPlanetRepository](-study-planet-repository/index.md) interface that communicates with both local and remote data sources. Application of facade pattern to abstract away the implementation details of the data sources. |
