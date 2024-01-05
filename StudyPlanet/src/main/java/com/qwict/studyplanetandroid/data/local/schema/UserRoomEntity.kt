package com.qwict.studyplanetandroid.data.local.schema

import android.util.Log
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.common.math.DoubleMath.log2
import com.qwict.studyplanetandroid.domain.model.User
import kotlin.math.ceil
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
 * Converts a [UserRoomEntity] into a [User] domain model.
 *
 * @return A [User] instance.
 */
fun UserRoomEntity.asDomainModel(): User {
    val experience = this.experience
    var currentLevel = 0

    Log.d("calculateLevel", "experience: $experience")
    if (ceil(log2(experience.toDouble() / 60)) > 0) {
        currentLevel = (log2(experience.toDouble() / 60) + 1).toInt()
    }
    Log.d("calculateLevel", "currentLevel: $currentLevel")

    val experienceForCurrentLevel = (2.0.pow((currentLevel - 1).toDouble()) * 60).toInt()
    Log.d("calculateLevel", "experienceForCurrentLevel: $experienceForCurrentLevel")

    val experienceForNextLevel = (2.0.pow(currentLevel) * 60).toInt()
    Log.d("calculateLevel", "experienceForNextLevel: $experienceForNextLevel")

    val experienceProgress =
        if (currentLevel == 0) {
            (experience / experienceForNextLevel).toFloat()
        } else {
            (
                (experience - experienceForCurrentLevel).toDouble() /
                    (experienceForNextLevel - experienceForCurrentLevel).toDouble()
            ).toFloat()
        }
    Log.d("calculateLevel", "experienceProgress: $experienceProgress")

    val currentLevelProgress =
        if (currentLevel != 0) {
            experience - experienceForCurrentLevel
        } else {
            experience
        }

    return User(
        discoveredPlanets = emptyList(),
        email = email,
        name = name,
        experience = experience,
        currentLevel = currentLevel,
        currentLevelProgress = currentLevelProgress,
        experienceForCurrentLevel = experienceForCurrentLevel,
        experienceForNextLevel = experienceForNextLevel,
        experienceProgress = experienceProgress,
    )
}
