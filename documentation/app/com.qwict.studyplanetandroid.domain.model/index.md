//[app](../../index.md)/[com.qwict.studyplanetandroid.domain.model](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [Health](-health/index.md) | [androidJvm]<br>data class [Health](-health/index.md)(val version: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [Planet](-planet/index.md) | [androidJvm]<br>class [Planet](-planet/index.md)(val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val imageId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = getImageByName(StudyPlanetApplication.appContext, name))<br>Represents a planet in the Study Planet application. |
| [User](-user/index.md) | [androidJvm]<br>data class [User](-user/index.md)(val discoveredPlanets: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Planet](-planet/index.md)&gt;, val email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val experience: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Functions

| Name | Summary |
|---|---|
| [getImageByName](get-image-by-name.md) | [androidJvm]<br>fun [getImageByName](get-image-by-name.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), imageName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Retrieves the resource ID of an image by its name. |
