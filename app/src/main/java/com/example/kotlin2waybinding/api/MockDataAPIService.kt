package com.example.kotlin2waybinding.api

import com.example.kotlin2waybinding.model.Player
import retrofit2.http.GET
import retrofit2.http.Path

interface MockDataAPIService {

    @GET("players")
    suspend fun getPlayers(): List<Player>

    @GET("players/{id}")
    suspend fun getPlayer(@Path("id") id: Long): Player

}