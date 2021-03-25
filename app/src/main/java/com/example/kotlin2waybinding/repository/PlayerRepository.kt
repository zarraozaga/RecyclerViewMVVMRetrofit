package com.example.kotlin2waybinding.repository

import com.example.kotlin2waybinding.api.MetaphorAPIService
import com.example.kotlin2waybinding.api.MockDataAPIService
import com.example.kotlin2waybinding.model.Player

class PlayerRepository(private val mockDataApiService: MockDataAPIService, private val metaphorAPIService: MetaphorAPIService) {
    suspend fun getPlayers() :  List<Player> {
        val playersRawList : List<Player> = mockDataApiService.getPlayers()
        return playersRawList
    }

    suspend fun getPlayer(id : Long) :  Player {
        val playerDetails : Player = mockDataApiService.getPlayer(id)
        return playerDetails
    }

    suspend fun getPlayerInformation () : String {
        val playerInfo: String = metaphorAPIService.getPlayerInfo()
        return playerInfo
    }
}



