package com.qwict.studyplanetandroid.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val experience: Int = 0,
)

fun populateUserData(): List<User> {
    return listOf(
        User(1, 5),
    )
}

val users = populateUserData()
