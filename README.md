# Study Planet Android App



## TL;DR
The Study Planet Android App is designed to help users stay focused and productive by incorporating gamification elements into their learning experience.
The app encourages users to concentrate on their studies or work by allowing them to discover and explore virtual planets on their Android devices.

Benefits include enhanced focus, gamified learning, and productivity even offline. 
Dependencies include Room, Retrofit, Kotlin Coroutines, Dagger Hilt, OkHttp, and Gson. 
The backend is hosted at [sp.qwict.com/api](https://sp.qwict.com/api/v1/health/version). 
For detailed documentation, visit [doc.sp.qwict.com](https://doc.sp.qwict.com). 
Feedback and issues can be reported on the GitHub repository.

## Features

**User Authentication**
- Users can create an account by registering with their Android phone.
- Existing users can log in to access the app's features.

**Study Mode**
- Once authenticated, users enter study mode where they can focus on their tasks without distractions.

**Planet Discovery**
- Users can discover new planets in the background while they concentrate on their studies or work.
- Each discovered planet contributes to the user's overall progress and experience.

**Planet Exploration**
- Users have the option to explore the planets they've discovered, earning additional experience points.
- Exploration adds an interactive and rewarding element to the learning experience.

**Experience Points (XP) System**
- Users accumulate experience points based on their discovered planets and exploration activities.
- The XP system provides a measure of the user's dedication to focused learning.

**Offline Learning**
- The app is designed to operate in the background, allowing users to learn even without actively using their phones.
- Users can track their progress and achievements over time.
  
## Benefits

  Focus Enhancement: Encourages users to concentrate for longer periods by providing a tangible reward system.
  Gamification of Learning: Adds a playful element to the learning process, making it more engaging and enjoyable.
  Offline Productivity: Facilitates learning even when the user is not actively interacting with the app.

## Documentation

The documentation for StudyPlanet is generated using **Dokka**. 
It is online available on [doc.sp.qwict.com](https://doc.sp.qwict.com).
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
The connection to this backend is defined in [Constants](https://doc.sp.qwict.com/app/com.qwict.studyplanetandroid.common/-constants/) as `BASE_URL`.

## Feedback and Issues

If you encounter any issues or have feedback, please feel free to open an issue on the GitHub repository.

Happy learning and exploring with Study Planet!