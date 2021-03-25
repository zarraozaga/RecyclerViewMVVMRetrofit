package com.example.kotlin2waybinding.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitBuilder {
    private val RETROFIT_MOCK_DATA_URL = "https://604b0300ee7cb900176a1190.mockapi.io/api/coroutine/"
    private val RETROFIT_METAPHORSUM_URL = "http://metaphorpsum.com/" //1/6
    
    val mockDataAPIService: MockDataAPIService = getRetrofitMockAPI().create(MockDataAPIService::class.java)
    val metaphorsumAPIService: MetaphorAPIService = getRetrofitMetaphorAPI().create(MetaphorAPIService::class.java)

    private fun getRetrofitMockAPI(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RETROFIT_MOCK_DATA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    private fun getRetrofitMetaphorAPI(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RETROFIT_METAPHORSUM_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build() //Doesn't require the adapter
    }

}