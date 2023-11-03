package com.qwict.studyplanetandroid.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.getUUID
import com.qwict.studyplanetandroid.domain.model.User
import java.util.UUID

@Entity(
    tableName = "users",
    indices = [
        Index(
            value = arrayOf("userUuid"),
            unique = true,
        ),
    ],
)
data class DatabaseUser(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var userUuid: UUID = getUUID(),
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
    discoveredPlanets = planets.map { it.toPlanet() },
    email = user.email,
    experience = user.experience,
    id = user.id,
    name = user.name,
)

fun DatabaseUser.toUser() = User(
    discoveredPlanets = emptyList(),
    email = email,
    experience = experience,
    id = id,
    name = name,
)
