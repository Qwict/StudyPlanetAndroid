package com.qwict.studyplanetandroid.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var experience: Int = 0,
)

fun populateUserData(): List<User> {
    return listOf(
        User(1, 5),
    )
}

data class UserWithPlanets(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
    )
    val playlists: List<Planet>,
)
