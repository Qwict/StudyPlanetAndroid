//[app](../../../index.md)/[com.qwict.studyplanetandroid.common.di](../index.md)/[AppModule](index.md)/[provideUserDatabase](provide-user-database.md)

# provideUserDatabase

[androidJvm]\

@Provides

@Singleton

fun [provideUserDatabase](provide-user-database.md)(): [OfflineUsersRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-users-repository/index.md)

Provides an instance of [OfflineUsersRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-users-repository/index.md) backed by the StudyPlanetDatabase.

This function creates and returns an implementation of [OfflineUsersRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-users-repository/index.md) that utilizes the StudyPlanetDatabase. It initializes the database with the application context and a CoroutineScope, and retrieves the UserDao for user data access. The returned [OfflineUsersRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-users-repository/index.md) instance is configured as a singleton.

#### Return

An instance of [OfflineUsersRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-users-repository/index.md) using the StudyPlanetDatabase.
