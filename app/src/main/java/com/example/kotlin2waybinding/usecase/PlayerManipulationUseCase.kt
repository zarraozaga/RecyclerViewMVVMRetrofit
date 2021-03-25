package com.example.kotlin2waybinding.usecase

import com.example.kotlin2waybinding.model.Player

interface PlayerManipulationUseCase {
    suspend fun getPlayersList() : List<Player>

    suspend fun getPlayer(id : Long) : Player
}