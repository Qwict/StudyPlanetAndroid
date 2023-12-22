//[app](../../index.md)/[com.qwict.studyplanetandroid.common](index.md)/[getDecodedPayload](get-decoded-payload.md)

# getDecodedPayload

[androidJvm]\
fun [getDecodedPayload](get-decoded-payload.md)(token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [DecodedPayload](-decoded-payload/index.md)

Decodes a JSON Web Token (JWT) and extracts information from its payload.

This function uses the JWT library to parse the JWT and retrieve specific claims from its payload. It constructs and returns a [DecodedPayload](-decoded-payload/index.md) object containing the decoded information, including email, remote ID, issued-at time (iat), and expiration time (exp).

If any decoding error or null values are encountered, the function removes the token from preferences and throws an [IllegalArgumentException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-argument-exception/index.html) with an error message.

#### Return

A [DecodedPayload](-decoded-payload/index.md) object containing decoded information from the JWT payload.

#### Parameters

androidJvm

| | |
|---|---|
| token | The JSON Web Token to decode and extract payload information from. |

#### Throws

| | |
|---|---|
| [IllegalArgumentException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-argument-exception/index.html) | if the JWT is not valid or contains null values in required claims. |
