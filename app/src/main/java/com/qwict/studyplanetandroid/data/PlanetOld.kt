package com.qwict.studyplanetandroid.data

import android.util.Log
import androidx.annotation.DrawableRes
import com.qwict.studyplanetandroid.R

class OldPlanet constructor(
    id: Int = 0,
    name: String = "Unknown Planet",
    @DrawableRes imageId: Int = R.drawable.earth,
) {
    val id = id
    val name = name
    val imageId = imageId

    init {
        Log.d("Planet", "Planet created with name: $name and id: $id")
    }

    override fun equals(other: Any?): Boolean {
        if (other is OldPlanet) {
            return this.id == other.id
        }
        return false
    }
}
