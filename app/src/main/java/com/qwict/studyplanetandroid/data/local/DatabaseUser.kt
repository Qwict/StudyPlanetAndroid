package com.qwict.studyplanetandroid.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.qwict.studyplanetandroid.domain.model.User
import com.qwict.studyplanetandroid.common.getEncryptedPreference
import java.util.UUID

@Entity(tableName = "users")
data class DatabaseUser(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var userUuid: UUID = getEncryptedPreference("userUuid").let { UUID.fromString(it) },
    var experience: Int = 0,
    var remoteId: Int = 0,
    var email: String = "",
    var name: String = "",
)

data class DatabaseUserWithPlanets(
    @Embedded val user: DatabaseUser,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
    )
    val planets: List<DatabasePlanet>,
)

fun DatabaseUserWithPlanets.toUser() = User(
    discoveredPlanets = planets.map { it.toPlanet(user) },
    email = "",
    experience = user.experience,
    id = user.id,
    name = "",
)
