package com.qwict.studyplanetandroid.common

// Based on great tutorial by Philipp Lackner:

/**
 * A sealed class representing different states of a resource, typically used for data fetching operations.
 *
 * The [Resource] class has three subclasses:
 * - [Loading]: Indicates that the data is currently being loaded.
 * - [Success]: Indicates that the data has been successfully loaded.
 * - [Error]: Indicates that an error occurred during data loading, with an associated error message.
 *
 * @param T The type of data held by the resource.
 * @property data The data associated with the resource, if available.
 * @property message A descriptive message associated with the resource, usually for error states.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    /**
     * Represents the loading state of a resource.
     *
     * @param T The type of data being loaded.
     * @property data The data being loaded, if available.
     */
    class Loading<T>(data: T? = null) : Resource<T>(data)

    /**
     * Represents the success state of a resource.
     *
     * @param T The type of data loaded successfully.
     * @property data The successfully loaded data.
     */
    class Success<T>(data: T) : Resource<T>(data)

    /**
     * Represents the error state of a resource.
     *
     * @param T The type of data for which an error occurred.
     * @property message A descriptive message explaining the error.
     * @property data The data associated with the error, if available.
     */
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
