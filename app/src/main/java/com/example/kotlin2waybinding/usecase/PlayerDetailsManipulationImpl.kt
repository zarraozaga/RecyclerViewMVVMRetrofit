package com.example.kotlin2waybinding.usecase

import com.example.kotlin2waybinding.R
import com.example.kotlin2waybinding.model.Player
import com.example.kotlin2waybinding.repository.PlayerRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerDetailsManipulationImpl (val playerRepository: PlayerRepository) : PlayerManipulationUseCase {
    override suspend fun getPlayersList() : List<Player>{
        val players : List<Player> = playerRepository.getPlayers()
        for (i in players) {
            i.picture = when (i.picture % 4) {
                1 -> R.drawable.defender
                2 -> R.drawable.midfield
                3 -> R.drawable.striker
                else -> R.drawable.keeper
            }

            i.stats = i.stats.toString().take(2).toInt()
        }

        return players
    }
}