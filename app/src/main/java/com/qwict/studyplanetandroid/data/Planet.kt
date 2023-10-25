package com.qwict.studyplanetandroid.data

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.qwict.studyplanetandroid.R

@Entity(tableName = "planets")
data class Planet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val remoteId: Int = 0,
    val name: String = "Unknown Planet",
    val imageId: Int = R.drawable.earth,
)

fun populatePlanetData(): List<Planet> {
    Log.d("PlanetEntity", "populateData called")
    return listOf(
        Planet(1, 1, "Earth", R.drawable.earth),
        Planet(2, 2, "Mars", R.drawable.mars),
        Planet(3, 3, "Europe", R.drawable.europa),
    )
    Log.d("PlanetEntity", "populated database with planets")
}

val planets = populatePlanetData()
