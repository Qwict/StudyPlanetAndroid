//[app](../../index.md)/[com.qwict.studyplanetandroid.common](index.md)/[getEncryptedPreference](get-encrypted-preference.md)

# getEncryptedPreference

[androidJvm]\
fun [getEncryptedPreference](get-encrypted-preference.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

Retrieves an encrypted preference value associated with the given [key](get-encrypted-preference.md).

This function fetches an encrypted preference value stored in the SharedPreferences. It uses the getSharedPreferences function to access the SharedPreferences instance. If the preference value for the specified [key](get-encrypted-preference.md) is not found, an empty string is returned.

#### Return

The encrypted preference value for the given [key](get-encrypted-preference.md) or an empty string if not found.

#### Parameters

androidJvm

| | |
|---|---|
| key | The key associated with the preference value to retrieve. |
