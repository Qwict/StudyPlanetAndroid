package com.qwict.studyplanetandroid.data.local

import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface OfflineUsersRepository {
    fun getUserById(id: Int): Flow<DatabaseUser>
    fun getUserFlowByUuid(uuid: UUID): Flow<DatabaseUser>
    suspend fun getUserByUuid(uuid: UUID): DatabaseUser
    suspend fun insert(user: DatabaseUser)
    suspend fun insertAll(users: List<DatabaseUser>)
    suspend fun update(user: DatabaseUser)
    suspend fun delete(user: DatabaseUser)
}
