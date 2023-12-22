//[app](../../../index.md)/[com.qwict.studyplanetandroid.presentation.viewmodels.states](../index.md)/[UserState](index.md)

# UserState

[androidJvm]\
data class [UserState](index.md)(val isLoading: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, val isRefreshing: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, val refreshError: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, val error: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, val user: [User](../../com.qwict.studyplanetandroid.domain.model/-user/index.md) = EMPTY_USER, val planets: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Planet](../../com.qwict.studyplanetandroid.domain.model/-planet/index.md)&gt; = emptyList(), val experienceForNextLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, val currentLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, val experienceProgress: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 0.0f, val selectedTime: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0)

## Constructors

| | |
|---|---|
| [UserState](-user-state.md) | [androidJvm]<br>constructor(isLoading: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, isRefreshing: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, refreshError: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, error: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, user: [User](../../com.qwict.studyplanetandroid.domain.model/-user/index.md) = EMPTY_USER, planets: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Planet](../../com.qwict.studyplanetandroid.domain.model/-planet/index.md)&gt; = emptyList(), experienceForNextLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, currentLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, experienceProgress: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 0.0f, selectedTime: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0) |

## Properties

| Name | Summary |
|---|---|
| [currentLevel](current-level.md) | [androidJvm]<br>val [currentLevel](current-level.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0 |
| [error](error.md) | [androidJvm]<br>val [error](error.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [experienceForNextLevel](experience-for-next-level.md) | [androidJvm]<br>val [experienceForNextLevel](experience-for-next-level.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0 |
| [experienceProgress](experience-progress.md) | [androidJvm]<br>val [experienceProgress](experience-progress.md): [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 0.0f |
| [isLoading](is-loading.md) | [androidJvm]<br>val [isLoading](is-loading.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false |
| [isRefreshing](is-refreshing.md) | [androidJvm]<br>val [isRefreshing](is-refreshing.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false |
| [planets](planets.md) | [androidJvm]<br>val [planets](planets.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Planet](../../com.qwict.studyplanetandroid.domain.model/-planet/index.md)&gt; |
| [refreshError](refresh-error.md) | [androidJvm]<br>val [refreshError](refresh-error.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [selectedTime](selected-time.md) | [androidJvm]<br>val [selectedTime](selected-time.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0 |
| [user](user.md) | [androidJvm]<br>val [user](user.md): [User](../../com.qwict.studyplanetandroid.domain.model/-user/index.md) |
