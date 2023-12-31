package com.qwict.studyplanetandroid.data.local.schema

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.common.math.DoubleMath.log2
import com.qwict.studyplanetandroid.domain.model.User
import kotlin.math.pow

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
        entityColumn = "ownerId",
    )
    val planets: List<PlanetRoomEntity>,
)

/**
 * Converts a [DatabaseUserWithPlanets] into a [User] domain model.
 *
 * @return A [User] instance.
 */
fun DatabaseUserWithPlanets.asDomainModel() = User(
    discoveredPlanets = planets.map { it.asDomainModel() },
    email = user.email,
    name = user.name,
    experience = user.experience,
    currentLevel = 0,
    experienceForCurrentLevel = 0,
    experienceForNextLevel = 0,
    experienceProgress = 0.0f,
)

/**
 * Converts a [UserRoomEntity] into a [User] domain model.
 *
 * @return A [User] instance.
 */
fun UserRoomEntity.asDomainModel() = User(
    discoveredPlanets = emptyList(),
    email = email,
    experience = experience,
    name = name,
    currentLevel = calculateLevel(experience),
    experienceForCurrentLevel = calculateExperienceForCurrentLevel(calculateLevel(experience)),
    experienceForNextLevel = calculateExperienceForNextLevel(calculateLevel(experience)),
    experienceProgress = calculateExperienceProgress(
        experience = experience,
        experienceForCurrentLevel = calculateExperienceForCurrentLevel(calculateLevel(experience)),
        experienceForNextLevel = calculateExperienceForNextLevel(calculateLevel(experience)),
    ),
)

private fun calculateLevel(experience: Int): Int {
    val level = kotlin.math.ceil(log2((experience / 60).toDouble()))
    return if (level < 0) {
        0
    } else {
        level.toInt()
    }
}

private fun calculateExperienceForCurrentLevel(level: Int): Int {
    return 2.0.pow((level - 1).toDouble()).toInt() * 60
}

private fun calculateExperienceForNextLevel(level: Int): Int {
    return 2.0.pow(level.toDouble()).toInt() * 60
}

private fun calculateExperienceProgress(experience: Int, experienceForCurrentLevel: Int, experienceForNextLevel: Int): Float {
    return if (experienceForCurrentLevel == 0) {
        experience / experienceForNextLevel.toFloat()
    } else {
        (experience - experienceForCurrentLevel) / (experienceForNextLevel - experienceForCurrentLevel).toFloat()
    }
}
