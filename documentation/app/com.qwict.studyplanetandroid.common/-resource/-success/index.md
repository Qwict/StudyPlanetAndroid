//[app](../../../../index.md)/[com.qwict.studyplanetandroid.common](../../index.md)/[Resource](../index.md)/[Success](index.md)

# Success

class [Success](index.md)&lt;[T](index.md)&gt;(data: [T](index.md)) : [Resource](../index.md)&lt;[T](index.md)&gt; 

Represents the success state of a resource.

#### Parameters

androidJvm

| | |
|---|---|
| T | The type of data loaded successfully. |

## Constructors

| | |
|---|---|
| [Success](-success.md) | [androidJvm]<br>constructor(data: [T](index.md)) |

## Properties

| Name | Summary |
|---|---|
| [data](../data.md) | [androidJvm]<br>val [data](../data.md): [T](index.md)?<br>The data associated with the resource, if available. |
| [message](../message.md) | [androidJvm]<br>val [message](../message.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>A descriptive message associated with the resource, usually for error states. |
