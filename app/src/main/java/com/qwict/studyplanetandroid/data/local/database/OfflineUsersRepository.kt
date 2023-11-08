package com.qwict.studyplanetandroid.data.local.database

import com.qwict.studyplanetandroid.data.local.schema.UserRoomEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface OfflineUsersRepository {
    fun getUserById(id: Int): Flow<UserRoomEntity>
    fun getUserFlowByUuid(uuid: UUID): Flow<UserRoomEntity>
    suspend fun getUserByEmail(email: String): UserRoomEntity
    suspend fun getUserByUuid(uuid: UUID): UserRoomEntity
    suspend fun insert(user: UserRoomEntity)
    suspend fun insertAll(users: List<UserRoomEntity>)
    suspend fun update(user: UserRoomEntity)
    suspend fun delete(user: UserRoomEntity)
}
