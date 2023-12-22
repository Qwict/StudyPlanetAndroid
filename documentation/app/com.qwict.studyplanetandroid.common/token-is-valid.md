//[app](../../index.md)/[com.qwict.studyplanetandroid.common](index.md)/[tokenIsValid](token-is-valid.md)

# tokenIsValid

[androidJvm]\
fun [tokenIsValid](token-is-valid.md)(token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Checks the validity of a JSON Web Token (JWT) by examining its decoded payload.

This function takes a JWT as input, decodes its payload using [getDecodedPayload](get-decoded-payload.md), and compares the expiration time (exp) claim with the current time to determine if the token is still valid.

If the token is not empty, the function logs the decoded payload and checks if its expiration time is greater than the current time. If valid, the function returns true; otherwise, it saves an &quot;expired&quot; token preference and returns false.

#### Return

`true` if the token is valid, `false` otherwise.

#### Parameters

androidJvm

| | |
|---|---|
| token | The JSON Web Token to check for validity. |
