package com.example.kotlin2waybinding.api

import com.example.kotlin2waybinding.model.Player
import retrofit2.http.GET

interface MetaphorAPIService {

    @GET("paragraphs/1/20")
    suspend fun getPlayerInfo(): String

}