package com.qwict.studyplanetandroid.data.local.database

import com.qwict.studyplanetandroid.data.local.dao.UserDao
import com.qwict.studyplanetandroid.data.local.schema.UserRoomEntity
import kotlinx.coroutines.flow.Flow

class OfflineUsersRepositoryImpl(private val userDao: UserDao) : OfflineUsersRepository {
    override fun getUserById(id: Int): Flow<UserRoomEntity> {
        return userDao.getFlowUserById(id)
    }

    override suspend fun getUserByRemoteId(remoteId: Int): UserRoomEntity {
        return userDao.getUserByRemoteId(remoteId)
    }
    override suspend fun insert(user: UserRoomEntity) {
        userDao.insert(user)
    }

    override suspend fun insertAll(users: List<UserRoomEntity>) {
        userDao.insertAll(users)
    }

    override suspend fun update(user: UserRoomEntity) {
        userDao.update(user)
    }

    override suspend fun delete(user: UserRoomEntity) {
        userDao.delete(user)
    }
}
