package com.qwict.studyplanetandroid.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.qwict.svkandroid.helper.getEncryptedPreference
import java.util.UUID

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var userUUID: UUID = getEncryptedPreference("userUuid").let { UUID.fromString(it) },
    var experience: Int = 0,
)

fun populateUserData(): List<User> {
    return listOf(
        User(1, getEncryptedPreference("userUuid").let { UUID.fromString(it) }, 0),
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
