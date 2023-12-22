//[app](../../../index.md)/[com.qwict.studyplanetandroid.domain.model](../index.md)/[Planet](index.md)

# Planet

[androidJvm]\
class [Planet](index.md)(val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val imageId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = getImageByName(StudyPlanetApplication.appContext, name))

Represents a planet in the Study Planet application.

## Constructors

| | |
|---|---|
| [Planet](-planet.md) | [androidJvm]<br>constructor(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), imageId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = getImageByName(StudyPlanetApplication.appContext, name)) |

## Properties

| Name | Summary |
|---|---|
| [id](id.md) | [androidJvm]<br>val [id](id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The unique identifier of the planet. |
| [imageId](image-id.md) | [androidJvm]<br>val [imageId](image-id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The resource ID of the planet's image. Defaults to the image associated with the planet's name. |
| [name](name.md) | [androidJvm]<br>val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of the planet. |
