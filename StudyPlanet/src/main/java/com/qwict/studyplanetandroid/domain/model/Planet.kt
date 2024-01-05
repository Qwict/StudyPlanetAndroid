package com.qwict.studyplanetandroid.domain.model

import android.annotation.SuppressLint
import android.content.Context
import com.qwict.studyplanetandroid.StudyPlanetApplication
import com.qwict.studyplanetandroid.common.Constants

/**
 * Represents a planet in the Study Planet application.
 *
 * @property id The unique identifier of the planet.
 * @property name The name of the planet.
 * @property imageId The resource ID of the planet's image. Defaults to the image associated with the planet's name.
 */
class Planet(
    val id: Int,
    val name: String,
    val imageId: Int = getImageByName(StudyPlanetApplication.appContext, name),
    val smallImageId: Int = getImageByName(StudyPlanetApplication.appContext, name + "_small"),
)

/**
 * Retrieves the resource ID of an image by its name.
 *
 * This function searches for an image resource with the specified name in the drawable resources.
 * If the image is not found, it returns the default planet image resource ID.
 *
 * @param context The application context.
 * @param imageName The name of the image to search for.
 * @return The resource ID of the image, or the default planet image resource ID if not found.
 */
@SuppressLint("DiscouragedApi")
fun getImageByName(
    context: Context,
    imageName: String,
): Int {
    val resources = context.resources
    val imageId = resources.getIdentifier(imageName.lowercase(), "drawable", context.packageName)
    return if (imageId != 0) {
        imageId
    } else {
        if (imageName.contains("_small")) {
            // Image not found, return the default small image
            return Constants.DEFAULT_PLANET_SMALL_IMAGE_ID
        }
        // Image not found, return the default image
        return Constants.DEFAULT_PLANET_IMAGE_ID
    }
}
