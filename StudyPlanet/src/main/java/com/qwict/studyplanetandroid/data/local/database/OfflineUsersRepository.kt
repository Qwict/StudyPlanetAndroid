package com.qwict.studyplanetandroid.data.local.database

import com.qwict.studyplanetandroid.data.local.schema.UserRoomEntity
import kotlinx.coroutines.flow.Flow

interface OfflineUsersRepository {
    fun getUserById(id: Int): Flow<UserRoomEntity>
    suspend fun getUserByRemoteId(remoteId: Int): UserRoomEntity
    suspend fun insert(user: UserRoomEntity)
    suspend fun insertAll(users: List<UserRoomEntity>)
    suspend fun update(user: UserRoomEntity)
    suspend fun delete(user: UserRoomEntity)
}
