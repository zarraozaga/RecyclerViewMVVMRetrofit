package com.example.kotlin2waybinding.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private val BASE_URL = "https://604b0300ee7cb900176a1190.mockapi.io/api/coroutine/"
    val apiService: APIService = getRetrofit().create(APIService::class.java)

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

}