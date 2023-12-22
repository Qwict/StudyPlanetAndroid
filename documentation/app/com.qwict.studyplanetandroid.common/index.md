//[app](../../index.md)/[com.qwict.studyplanetandroid.common](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [AuthenticationSingleton](-authentication-singleton/index.md) | [androidJvm]<br>@Singleton<br>object [AuthenticationSingleton](-authentication-singleton/index.md)<br>Singleton object responsible for managing user authentication state and related operations. |
| [AuthInterceptor](-auth-interceptor/index.md) | [androidJvm]<br>class [AuthInterceptor](-auth-interceptor/index.md) : Interceptor<br>Interceptor for adding the Authorization header with the user token to outgoing network requests. |
| [Constants](-constants/index.md) | [androidJvm]<br>object [Constants](-constants/index.md) |
| [DecodedHeader](-decoded-header/index.md) | [androidJvm]<br>data class [DecodedHeader](-decoded-header/index.md)(val alg: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val typ: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [DecodedPayload](-decoded-payload/index.md) | [androidJvm]<br>data class [DecodedPayload](-decoded-payload/index.md)(val remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val iat: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val exp: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [DecodedUser](-decoded-user/index.md) | [androidJvm]<br>data class [DecodedUser](-decoded-user/index.md)(val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val uuid: [UUID](https://developer.android.com/reference/kotlin/java/util/UUID.html), val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [Resource](-resource/index.md) | [androidJvm]<br>sealed class [Resource](-resource/index.md)&lt;[T](-resource/index.md)&gt;<br>A sealed class representing different states of a resource, typically used for data fetching operations. |

## Properties

| Name | Summary |
|---|---|
| [masterKeyAlias](master-key-alias.md) | [androidJvm]<br>val [masterKeyAlias](master-key-alias.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Functions

| Name | Summary |
|---|---|
| [getDecodedPayload](get-decoded-payload.md) | [androidJvm]<br>fun [getDecodedPayload](get-decoded-payload.md)(token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [DecodedPayload](-decoded-payload/index.md)<br>Decodes a JSON Web Token (JWT) and extracts information from its payload. |
| [getEncryptedPreference](get-encrypted-preference.md) | [androidJvm]<br>fun [getEncryptedPreference](get-encrypted-preference.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Retrieves an encrypted preference value associated with the given [key](get-encrypted-preference.md). |
| [removeEncryptedPreference](remove-encrypted-preference.md) | [androidJvm]<br>fun [removeEncryptedPreference](remove-encrypted-preference.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Removes the encrypted preference value associated with the given [key](remove-encrypted-preference.md). |
| [saveEncryptedPreference](save-encrypted-preference.md) | [androidJvm]<br>fun [saveEncryptedPreference](save-encrypted-preference.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), preference: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Saves an encrypted preference value for the given [key](save-encrypted-preference.md). |
| [tokenIsValid](token-is-valid.md) | [androidJvm]<br>fun [tokenIsValid](token-is-valid.md)(token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks the validity of a JSON Web Token (JWT) by examining its decoded payload. |
