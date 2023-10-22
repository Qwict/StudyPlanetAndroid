package com.qwict.studyplanetandroid.data

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.qwict.studyplanetandroid.R

@Entity(tableName = "planets")
data class PlanetEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val remoteId: Int = 0,
    val name: String = "Unknown Planet",
    val imageId: Int = R.drawable.earth,
)

fun populateData(): List<PlanetEntity> {
    Log.d("PlanetEntity", "populateData called")
    return listOf(
        PlanetEntity(1, 1, "Earth", R.drawable.earth),
        PlanetEntity(2, 2, "Mars", R.drawable.mars),
        PlanetEntity(3, 3, "Europe", R.drawable.europa),
    )
    Log.d("PlanetEntity", "populated database with planets")
}

val planets = populateData()
