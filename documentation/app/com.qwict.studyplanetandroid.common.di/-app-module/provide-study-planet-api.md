//[app](../../../index.md)/[com.qwict.studyplanetandroid.common.di](../index.md)/[AppModule](index.md)/[provideStudyPlanetApi](provide-study-planet-api.md)

# provideStudyPlanetApi

[androidJvm]\

@Provides

@Singleton

fun [provideStudyPlanetApi](provide-study-planet-api.md)(): [StudyPlanetApi](../../com.qwict.studyplanetandroid.data.remote/-study-planet-api/index.md)

Provides an instance of [StudyPlanetApi](../../com.qwict.studyplanetandroid.data.remote/-study-planet-api/index.md) using Retrofit.

This function configures and creates a Retrofit instance for interacting with the StudyPlanet API. It uses the [BASE_URL](../../com.qwict.studyplanetandroid.common/-constants/-b-a-s-e_-u-r-l.md) as the base URL and sets up GsonConverterFactory for JSON serialization. Additionally, it uses a custom OkHttpClient configured by [okhttpClient](okhttp-client.md).

#### Return

An instance of [StudyPlanetApi](../../com.qwict.studyplanetandroid.data.remote/-study-planet-api/index.md) for making API requests.
