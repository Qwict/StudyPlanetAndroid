//[app](../../index.md)/[com.qwict.studyplanetandroid.common](index.md)/[saveEncryptedPreference](save-encrypted-preference.md)

# saveEncryptedPreference

[androidJvm]\
fun [saveEncryptedPreference](save-encrypted-preference.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), preference: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Saves an encrypted preference value for the given [key](save-encrypted-preference.md).

This function stores the provided [preference](save-encrypted-preference.md) as an encrypted value in the SharedPreferences using the specified [key](save-encrypted-preference.md). It uses the getSharedPreferences function to access the SharedPreferences instance.

#### Parameters

androidJvm

| | |
|---|---|
| key | The key under which the preference value will be stored. |
| preference | The encrypted preference value to be saved. |
