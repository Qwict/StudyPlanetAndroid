//[app](../../index.md)/[com.qwict.studyplanetandroid.domain.model](index.md)/[getImageByName](get-image-by-name.md)

# getImageByName

[androidJvm]\
fun [getImageByName](get-image-by-name.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), imageName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Retrieves the resource ID of an image by its name.

This function searches for an image resource with the specified name in the drawable resources. If the image is not found, it returns the default planet image resource ID.

#### Return

The resource ID of the image, or the default planet image resource ID if not found.

#### Parameters

androidJvm

| | |
|---|---|
| context | The application context. |
| imageName | The name of the image to search for. |
