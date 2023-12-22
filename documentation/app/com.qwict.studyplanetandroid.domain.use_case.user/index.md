//[app](../../index.md)/[com.qwict.studyplanetandroid.domain.use_case.user](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [AuthenticateUseCase](-authenticate-use-case/index.md) | [androidJvm]<br>class [AuthenticateUseCase](-authenticate-use-case/index.md)@Injectconstructor(repo: [StudyPlanetRepository](../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md))<br>Use case for authenticating the user and fetching user information. |
| [LoginUseCase](-login-use-case/index.md) | [androidJvm]<br>class [LoginUseCase](-login-use-case/index.md)@Injectconstructor(repo: [StudyPlanetRepository](../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md))<br>Use case for handling user login. |
| [RegisterUseCase](-register-use-case/index.md) | [androidJvm]<br>class [RegisterUseCase](-register-use-case/index.md)@Injectconstructor(repo: [StudyPlanetRepository](../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md))<br>Use case for handling user registration. |

## Functions

| Name | Summary |
|---|---|
| [insertLocalUserUseCase](insert-local-user-use-case.md) | [androidJvm]<br>fun [insertLocalUserUseCase](insert-local-user-use-case.md)(authenticatedUserDto: [AuthenticatedUserDto](../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md)): [User](../com.qwict.studyplanetandroid.domain.model/-user/index.md)<br>Use case for inserting a locally authenticated user. |
