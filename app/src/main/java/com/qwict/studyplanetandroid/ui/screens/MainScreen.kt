package com.qwict.studyplanetandroid.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qwict.studyplanetandroid.dto.HealthDto
import com.qwict.studyplanetandroid.dto.UserDto
import com.qwict.studyplanetandroid.helper.RetrofitSingleton
import com.qwict.studyplanetandroid.ui.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onStartExploringButtonClicked: () -> Unit = {},
    viewModel: MainViewModel,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val id = remember {
            mutableStateOf(TextFieldValue())
        }

        val profile = remember {
            mutableStateOf(
                UserDto(
                    id = "",
                    name = "",
                    email = "",
                    experience = 0,
                ),
            )
        }

        val health = remember {
            mutableStateOf(
                HealthDto(
                    env = "",
                    version = "",
                    name = "",
                ),
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            label = { Text(text = "User ID") },
            value = id.value,
            onValueChange = { id.value = it },
        )

        Spacer(modifier = Modifier.height(15.dp))

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    val data = sendRequest(
                        healthState = health,
                    )

                    Log.d("Main Activity", profile.toString())
                },
            ) {
                Text(text = "Get Data")
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = profile.component1().toString(), fontSize = 16.sp)
        OutlinedButton(onClick = { onStartExploringButtonClicked() }) {
            Text(text = "Start Exploring")
        }

        Text(text = health.component1().toString(), fontSize = 16.sp)
    }
}

fun sendRequest(
    healthState: MutableState<HealthDto>,
) {
    val healthCall: Call<HealthDto?>? = RetrofitSingleton.getApiService().getVersion()
    healthCall!!.enqueue(object : Callback<HealthDto?> {
        override fun onResponse(call: Call<HealthDto?>, response: Response<HealthDto?>) {
            if (response.isSuccessful) {
                Log.d("Main", "success!" + response.body().toString())
                healthState.value = response.body()!!
            }
        }

        override fun onFailure(call: Call<HealthDto?>, t: Throwable) {
            Log.e("Main", "Failed mate " + t.message.toString())
        }
    })
}
