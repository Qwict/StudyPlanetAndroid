//[app](../../../index.md)/[com.qwict.studyplanetandroid.common](../index.md)/[Resource](index.md)

# Resource

sealed class [Resource](index.md)&lt;[T](index.md)&gt;

A sealed class representing different states of a resource, typically used for data fetching operations.

The [Resource](index.md) class has three subclasses:

- 
   [Loading](-loading/index.md): Indicates that the data is currently being loaded.
- 
   [Success](-success/index.md): Indicates that the data has been successfully loaded.
- 
   [Error](-error/index.md): Indicates that an error occurred during data loading, with an associated error message.

#### Parameters

androidJvm

| | |
|---|---|
| T | The type of data held by the resource. |

#### Inheritors

| |
|---|
| [Loading](-loading/index.md) |
| [Success](-success/index.md) |
| [Error](-error/index.md) |

## Types

| Name | Summary |
|---|---|
| [Error](-error/index.md) | [androidJvm]<br>class [Error](-error/index.md)&lt;[T](-error/index.md)&gt;(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), data: [T](-error/index.md)? = null) : [Resource](index.md)&lt;[T](-error/index.md)&gt; <br>Represents the error state of a resource. |
| [Loading](-loading/index.md) | [androidJvm]<br>class [Loading](-loading/index.md)&lt;[T](-loading/index.md)&gt;(data: [T](-loading/index.md)? = null) : [Resource](index.md)&lt;[T](-loading/index.md)&gt; <br>Represents the loading state of a resource. |
| [Success](-success/index.md) | [androidJvm]<br>class [Success](-success/index.md)&lt;[T](-success/index.md)&gt;(data: [T](-success/index.md)) : [Resource](index.md)&lt;[T](-success/index.md)&gt; <br>Represents the success state of a resource. |

## Properties

| Name | Summary |
|---|---|
| [data](data.md) | [androidJvm]<br>val [data](data.md): [T](index.md)? = null<br>The data associated with the resource, if available. |
| [message](message.md) | [androidJvm]<br>val [message](message.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>A descriptive message associated with the resource, usually for error states. |
