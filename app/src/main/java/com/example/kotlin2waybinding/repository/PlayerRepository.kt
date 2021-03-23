package com.example.kotlin2waybinding.repository

import com.example.kotlin2waybinding.api.APIService
import com.example.kotlin2waybinding.model.Player

class PlayerRepository(private val apiService: APIService) {
    suspend fun getPlayers() :  List<Player> {
        val playersRawList : List<Player> = apiService.getPlayers()
        return playersRawList
    }
}



