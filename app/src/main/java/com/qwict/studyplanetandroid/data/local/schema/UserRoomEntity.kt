package com.qwict.studyplanetandroid.data.local.schema

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.qwict.studyplanetandroid.domain.model.User

/**
 * Room Entity representing a user in the local database.
 *
 * @property id The local database identifier for the user.
 * @property experience The experience level of the user.
 * @property remoteId The remote identifier of the user.
 * @property email The email address of the user.
 * @property name The name of the user.
 */
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

/**
 * Data class representing a user with associated planets in the local database.
 *
 * @property user The user information.
 * @property planets The list of planets associated with the user.
 */
data class DatabaseUserWithPlanets(
    @Embedded val user: UserRoomEntity,
    @Relation(
        parentColumn = "remoteId",
        entityColumn = "userOwnerId",
    )
    val planets: List<PlanetRoomEntity>,
)

/**
 * Converts a [DatabaseUserWithPlanets] into a [User] domain model.
 *
 * @return A [User] instance.
 */
fun DatabaseUserWithPlanets.asDomainModel() = User(
    discoveredPlanets = planets.map { it.toPlanet() },
    email = user.email,
    experience = user.experience,
    id = user.id,
    name = user.name,
)

/**
 * Converts a [UserRoomEntity] into a [User] domain model.
 *
 * @return A [User] instance.
 */
fun UserRoomEntity.toUser() = User(
    discoveredPlanets = emptyList(),
    email = email,
    experience = experience,
    id = id,
    name = name,
)
