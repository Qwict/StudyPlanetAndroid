package com.qwict.studyplanetandroid.data.local.database

import com.qwict.studyplanetandroid.data.local.schema.UserRoomEntity
import com.qwict.studyplanetandroid.data.local.dao.UserDao
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class OfflineUsersRepositoryImpl(private val userDao: UserDao) : OfflineUsersRepository {
    override fun getUserById(id: Int): Flow<UserRoomEntity> {
        return userDao.getFlowUserById(id)
    }

    override fun getUserFlowByUuid(uuid: UUID): Flow<UserRoomEntity> {
        return userDao.getFlowUserByUuid(uuid)
    }

    override suspend fun getUserByEmail(email: String): UserRoomEntity {
        return userDao.getUserByEmail(email)
    }

    override suspend fun getUserByUuid(uuid: UUID): UserRoomEntity {
        return userDao.getUserByUuid(uuid)
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
