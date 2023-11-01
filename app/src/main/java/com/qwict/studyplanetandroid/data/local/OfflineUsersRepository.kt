package com.qwict.studyplanetandroid.data.local

import kotlinx.coroutines.flow.Flow

interface OfflineUsersRepository {
    fun getUserById(id: Int): Flow<DatabaseUser>
    suspend fun insert(user: DatabaseUser)
    suspend fun insertAll(users: List<DatabaseUser>)
    suspend fun update(user: DatabaseUser)
    suspend fun delete(user: DatabaseUser)
}
