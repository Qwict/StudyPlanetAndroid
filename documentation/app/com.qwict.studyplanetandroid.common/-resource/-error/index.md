//[app](../../../../index.md)/[com.qwict.studyplanetandroid.common](../../index.md)/[Resource](../index.md)/[Error](index.md)

# Error

class [Error](index.md)&lt;[T](index.md)&gt;(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), data: [T](index.md)? = null) : [Resource](../index.md)&lt;[T](index.md)&gt; 

Represents the error state of a resource.

#### Parameters

androidJvm

| | |
|---|---|
| T | The type of data for which an error occurred. |

## Constructors

| | |
|---|---|
| [Error](-error.md) | [androidJvm]<br>constructor(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), data: [T](index.md)? = null) |

## Properties

| Name | Summary |
|---|---|
| [data](../data.md) | [androidJvm]<br>val [data](../data.md): [T](index.md)?<br>The data associated with the resource, if available. |
| [message](../message.md) | [androidJvm]<br>val [message](../message.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>A descriptive message associated with the resource, usually for error states. |
