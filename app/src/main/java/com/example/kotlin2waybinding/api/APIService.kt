package com.example.kotlin2waybinding.api

import com.example.kotlin2waybinding.model.Player
import retrofit2.http.GET

interface APIService {

    @GET("players")
    suspend fun getPlayers(): List<Player>

}