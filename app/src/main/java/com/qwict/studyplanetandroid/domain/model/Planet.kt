package com.qwict.studyplanetandroid.domain.model

import android.content.Context
import com.qwict.studyplanetandroid.StudyPlanetApplication
import com.qwict.studyplanetandroid.common.Constants

class Planet(
    val id: Int,
    val name: String,
    val imageId: Int = getImageByName(StudyPlanetApplication.appContext, name),
)
fun getImageByName(context: Context, imageName: String): Int {
    val resources = context.resources
    val imageId = resources.getIdentifier(imageName.lowercase(), "drawable", context.packageName)
    return if (imageId != 0) {
        imageId
    } else {
        // Image not found, return the default image
        return Constants.DEFAULT_PLANET_IMAGE_ID
    }
}
