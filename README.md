# Study Planet Android App



## TL;DR
The Study Planet Android App is designed to help users stay focused and productive by incorporating gamification elements into their learning experience.
The app encourages users to concentrate on their studies or work by allowing them to discover and explore virtual planets on their Android devices.

The backend is hosted at [sp.qwict.com/api](https://sp.qwict.com/api/v1/health/version) the code for this server is available at [Github/Qwict/StudyPlanetNodeAPI](https://github.com/Qwict/StudyPlanetNodeAPI). 
For detailed documentation, visit [docs.sp.qwict.com](https://docs.sp.qwict.com). 
The application is automatically built and released on [Github Releases](https://github.com/Qwict/StudyPlanetAndroid/releases).
An iOS version was also build and is available on [Github/Qwict/StudyPlanetiOS](https://github.com/Qwict/StudyPlanetiOS).
Feedback and issues can be reported on the [GitHub Issues](https://github.com/Qwict/StudyPlanetAndroid/issues).

## Demo

https://github.com/Qwict/StudyPlanetAndroid/assets/100297369/4f2cd849-c952-43e7-8c4b-424e28cc39c9

> This demo shows the application running in an Android simulator.
> It is combined with a locally hosted development backend to visualise the API calls that are performed by the application.
> Running the backend in development mode allows discover and explore actions under 15 minutes.
> The production version of the server requires actions that take atlease 15 minutes.

## Documentation

The documentation for StudyPlanet is generated using **Dokka**. 
It is online available on [docs.sp.qwict.com](https://docs.sp.qwict.com).
This documentation is updated automatically on every commit to the `main` branch.

## Dependencies

- [Room](https://developer.android.com/training/data-storage/room): Android SQLite database library.
- [Retrofit](https://square.github.io/retrofit): HTTP client for making API requests.
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html): Asynchronous programming in Kotlin.
- [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android): Dependency injection for Android.
- [OkHttp](https://square.github.io/okhttp/): HTTP client for intercepting requests (used in conjunction with Retrofit).
- [Gson](https://github.com/google/gson): JSON parsing library for Kotlin.

Make sure to check the `build.gradle.kts` file for the most up-to-date list of dependencies.

## Backend

The backend for Study Planet is hosted on [sp.qwict.com/api](https://sp.qwict.com/api/v1/health/version).
This backend is also available on Github at [Qwict/StudyPlanetNodeAPI](https://github.com/Qwict/StudyPlanetNodeAPI).
The connection to this backend is defined in [Constants](https://docs.sp.qwict.com/-study-planet/com.qwict.studyplanetandroid.common/-constants/index.html) as `BASE_URL`. The `/res/xml/network_security_config.xml` must also be changed according to this URL.

## Features

**User Authentication**
- Users can create an account by registering with their Android phone.
- Existing users can log in to access the app's features.

**Study Mode**
- Once authenticated, users can enter study mode where they can focus on their tasks without distractions.

**Planet Discovery**
- Users can discover new planets while they concentrate on their studies or work.
- Each discovered planet contributes to the user's overall progress and experience.

**Planet Exploration**
- Users have the option to explore the planets they've discovered, earning additional experience points.
- Exploration adds an interactive and rewarding element to the learning experience.

**Experience Points (XP) System**
- Users accumulate experience points based on their discovered planets and exploration activities.
- The XP system provides a measure of the user's dedication to focused learning.

**Offline Learning**
- The app is designed to be able to opperate offline, however offline work will not add online user progrss.

## Feedback and Issues

If you encounter any issues or have feedback, please feel free to open an issue on the GitHub repository.

Happy learning and exploring with Study Planet!
