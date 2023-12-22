//[app](../../../index.md)/[com.qwict.studyplanetandroid.domain.validator](../index.md)/[ValidationResult](index.md)

# ValidationResult

[androidJvm]\
data class [ValidationResult](index.md)(val successful: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val errorMessage: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;)

A data class representing the result of a validation process.

## Constructors

| | |
|---|---|
| [ValidationResult](-validation-result.md) | [androidJvm]<br>constructor(successful: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), errorMessage: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;) |

## Properties

| Name | Summary |
|---|---|
| [errorMessage](error-message.md) | [androidJvm]<br>val [errorMessage](error-message.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>A descriptive error message in case the validation was not successful. |
| [successful](successful.md) | [androidJvm]<br>val [successful](successful.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indicates whether the validation was successful. |
