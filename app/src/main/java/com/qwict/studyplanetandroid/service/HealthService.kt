package com.qwict.studyplanetandroid.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.qwict.studyplanetandroid.api.HealthApi
import com.qwict.studyplanetandroid.api.RetrofitClient
import com.qwict.studyplanetandroid.dto.ErrorResponse
import okhttp3.ResponseBody

class HealthService {
    private val retrofit = RetrofitClient.getClient()
    public val healthApi = retrofit.create(HealthApi::class.java)

    fun successfulHealthResponse() {
        val healthResponse = healthApi.getHealth()
            .execute()
        val successful = healthResponse.isSuccessful
        val httpStatusCode = healthResponse.code()
        val httpStatusMessage = healthResponse.message()

        val errorBody: ResponseBody? = healthResponse.errorBody()
        val mapper = ObjectMapper()
        val mappedBody: ErrorResponse? = errorBody?.let { notNullErrorBody ->
            mapper.readValue(notNullErrorBody.toString(), ErrorResponse::class.java)
        }

        val headers = healthResponse.headers()
        val customHeaderValue = headers["custom-header"]
    }

    fun getHealth(): String {
        val health = healthApi.getHealth().execute()
        if (health.isSuccessful) {
            return health.body()?.version ?: "0.0.0"
        } else {
            return "Server is offline"
        }
    }

}