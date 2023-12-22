//[app](../../../index.md)/[com.qwict.studyplanetandroid.domain.use_case.actions](../index.md)/[StopDiscoveringUseCase](index.md)/[invoke](invoke.md)

# invoke

[androidJvm]\
operator fun [invoke](invoke.md)(selectedTime: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): Flow&lt;[Resource](../../com.qwict.studyplanetandroid.common/-resource/index.md)&lt;[Planet](../../com.qwict.studyplanetandroid.domain.model/-planet/index.md)?&gt;&gt;

Invokes the use case to stop the process of discovering a new planet.

#### Return

Flow<Resource<Planet?>> representing the result of the operation.

#### Parameters

androidJvm

| | |
|---|---|
| selectedTime | The selected time for the discovery process. |
