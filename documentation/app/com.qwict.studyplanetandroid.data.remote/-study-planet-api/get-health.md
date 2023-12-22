//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.remote](../index.md)/[StudyPlanetApi](index.md)/[getHealth](get-health.md)

# getHealth

[androidJvm]\

@GET(value = &quot;v1/health/version&quot;)

abstract suspend fun [getHealth](get-health.md)(): [HealthDto](../../com.qwict.studyplanetandroid.data.remote.dto/-health-dto/index.md)

Checks the health and version of the Study Planet server.

#### Return

The [HealthDto](../../com.qwict.studyplanetandroid.data.remote.dto/-health-dto/index.md) representing the health and version information.
