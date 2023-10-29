package com.qwict.studyplanetandroid.ui.viewModels

//import android.util.Log
//import androidx.compose.runtime.MutableState
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.lifecycle.ViewModel
//import com.qwict.studyplanetandroid.api.Api
//import com.qwict.studyplanetandroid.data.toPlanet
//import com.qwict.studyplanetandroid.common.AuthenticationSingleton.validateUser
//import com.qwict.svkandroid.helper.saveEncryptedPreference
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.serialization.json.JsonArray
//import kotlinx.serialization.json.JsonObject
//import kotlinx.serialization.json.buildJsonObject
//import kotlinx.serialization.json.jsonObject
//import kotlinx.serialization.json.put
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import javax.inject.Inject
//
//@HiltViewModel
//class AuthViewModel @Inject constructor(
////    private val planetsRepository: PlanetsRepository,
////    private val usersRepository: UsersRepository,
//) : ViewModel() {
//    fun authenticateUser() {
//        Log.d("AuthenticationSingleton", "User was authenticated")
//        validateUser()
//    }
//
//    fun register(email: MutableState<TextFieldValue>, password: MutableState<TextFieldValue>): Boolean {
//        var success = false
//        val body = buildJsonObject {
//            put("name", email.value.text)
//            put("email", email.value.text)
//            put("password", password.value.text)
//        }
//        Log.i("MainViewModel", body.toString())
//
//        Api.service.register(body).enqueue(object :
//            Callback<JsonObject> {
//            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//                Log.d("MainViewModel", response.code().toString())
//                Log.d("MainViewModel", response.body().toString())
//                if (response.isSuccessful) {
//                    val token = response.body()!!["token"].toString().replace("\"", "")
//                    val jsonUser = response.body()!!["user"]
//                    createLocalUser(token, jsonUser as JsonObject)
//                    authenticateUser()
//                    success = true
//                } else {
//                    Log.e("MainViewModel", "Failed to Login")
//                }
//            }
//
//            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                Log.e("MainViewModel", "Failed to Login, Failure with message: ${t.message}")
//            }
//        })
//        return success
//    }
//
//    fun login(email: MutableState<TextFieldValue>, password: MutableState<TextFieldValue>): Boolean {
//        var success = false
//
//        Api.service.login(
//            buildJsonObject {
//                put("email", email.value.text)
//                put("password", password.value.text)
//            },
//        ).enqueue(object :
//            Callback<JsonObject> {
//            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//                if (response.isSuccessful) {
//                    val token = response.body()!!["token"].toString().replace("\"", "")
//                    val jsonUser = response.body()!!["user"]
//                    createLocalUser(token, jsonUser as JsonObject)
//                    authenticateUser()
//                    success = true
//                } else {
//                    Log.e("MainViewModel", "Failed to Login response was not successful")
//                }
//            }
//
//            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                Log.e("MainViewModel", "Failed to Login")
//            }
//        })
//        return success
//    }
//
//    fun createLocalUser(token: String, jsonUser: JsonObject) {
//        Log.i("MainViewModel", "Creating local user with jsonUser: $jsonUser")
//        val jsonPlanets = jsonUser["discoveredPlanets"] as JsonArray
//        val experience = jsonUser["experience"].toString().toInt()
//        val email = jsonUser["email"].toString().replace("\"", "")
//        val id = jsonUser["id"].toString().toInt()
//
//        Log.d(
//            "ApiViewModel",
//            "Creating local user with experience: $experience, id: $id and ${jsonPlanets.size} planets}",
//        )
//        saveEncryptedPreference("token", token)
//        saveEncryptedPreference("userId", id.toString())
//        saveEncryptedPreference("email", email)
//
//        val planets = jsonPlanets.map { planet -> toPlanet(planet.jsonObject, id) }
////        viewModelScope.launch {
////            planetsRepository.insertAll(planets)
////            usersRepository.insert(
////                User(
////                    id = id,
////                    experience = experience,
////                ),
////            )
////        }
//    }
//}
