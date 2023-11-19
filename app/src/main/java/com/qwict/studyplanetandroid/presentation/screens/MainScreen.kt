package com.qwict.studyplanetandroid.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.api.Api
import com.qwict.studyplanetandroid.data.remote.dto.HealthDto
import com.qwict.studyplanetandroid.presentation.components.LevelExperienceBar
import com.qwict.studyplanetandroid.presentation.viewmodels.states.UserState
import kotlinx.serialization.json.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onStartExploringButtonClicked: () -> Unit = {},
    onDiscoverPlanetsButtonClicked: () -> Unit = {},
    userState: UserState,
) {
    val health = remember {
        mutableStateOf(
            HealthDto(
                env = "",
                version = "",
                name = "",
            ),
        )
    }
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val buttonText: String

        //            UserPicture(
        //                url = viewModel.user.picture,
        //                description = viewModel.user.name,
        //            )
        UserInfoRow(
            label = stringResource(R.string.email_label),
            value = userState.user.email,
        )
        UserInfoRow(
            label = "username",
            value = userState.user.name,
        )
        LevelExperienceBar(
            level = userState.currentLevel,
            experience = userState.user.experience,
            experienceForNextLevel = userState.experienceForNextLevel,
            experienceProgress = userState.experienceProgress,
        )

        Row() {
//            VideoPlayer()
        }

        Column {
            OutlinedButton(onClick = { onDiscoverPlanetsButtonClicked() }) {
                Text(text = "Discover Planets")
            }
            OutlinedButton(onClick = { onStartExploringButtonClicked() }) {
                Text(text = "Start Exploring")
            }
        }

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Row {
                Button(
                    onClick = {
                        sendRequest(
                            healthState = health,
                        )
                    },
                ) {
                    Text(text = "Get Health")
                }
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = health.component1().toString(), fontSize = 16.sp)
    }
}

fun sendRequest(
    healthState: MutableState<HealthDto>,
) {
    Api.service.getVersion().enqueue(object : Callback<JsonObject> {
        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
            if (response.isSuccessful) {
                Log.d("Main", "success!" + response.body().toString())
                healthState.value = HealthDto(
                    env = response.body()!!["env"].toString(),
                    version = response.body()!!["version"].toString(),
                    name = response.body()!!["name"].toString(),
                )
            }
        }

        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            Log.e("Main", "Failed mate " + t.message.toString())
        }
    })
}

@Composable
fun UserInfoRow(
    label: String,
    value: String,
) {
    Row {
        Text(
            text = label,
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            ),
        )
        Spacer(
            modifier = Modifier.width(10.dp),
        )
        Text(
            text = value,
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontSize = 20.sp,
            ),
        )
    }
}
