package com.qwict.studyplanetandroid.data.local.schema

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.qwict.studyplanetandroid.domain.model.User

@Entity(
    tableName = "users",
    indices = [
        Index(
            value = arrayOf("remoteId"),
            unique = true,
        ),
    ],
)
data class UserRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var experience: Int = 0,
    var remoteId: Int = 0,
    var email: String = "",
    var name: String = "",
)

data class DatabaseUserWithPlanets(
    @Embedded val user: UserRoomEntity,
    @Relation(
        parentColumn = "remoteId",
        entityColumn = "userOwnerId",
    )
    val planets: List<PlanetRoomEntity>,
)

fun DatabaseUserWithPlanets.asDomainModel() = User(
    discoveredPlanets = planets.map { it.toPlanet() },
    email = user.email,
    experience = user.experience,
    id = user.id,
    name = user.name,
)

fun UserRoomEntity.toUser() = User(
    discoveredPlanets = emptyList(),
    email = email,
    experience = experience,
    id = id,
    name = name,
)
