package com.example.kotlin2waybinding.usecase

import com.example.kotlin2waybinding.R
import com.example.kotlin2waybinding.model.Player
import com.example.kotlin2waybinding.repository.PlayerRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerDetailsManipulationImpl(val playerRepository: PlayerRepository) :
    PlayerManipulationUseCase {
    override suspend fun getPlayersList(): List<Player> {
        val players: List<Player> = playerRepository.getPlayers()
        for (i in players) {
            val(temp_picture, temp_stats) = _player_manipulation(i.picture, i.stats)
            i.picture = temp_picture
            i.stats = temp_stats
        }

        return players
    }

    override suspend fun getPlayer(id: Long): Player {
        val player: Player = playerRepository.getPlayer(id)
        val playerInfo: String = playerRepository.getPlayerInformation()
        val(temp_pic, temp_stats) = _player_manipulation(player.picture, player.stats)
        player.picture = temp_pic
        player.stats = temp_stats
        player.details = playerInfo

        return player
    }

    private fun _player_manipulation(pictureNo: Int, stats: Int): Pair<Int, Int> {
        val picture: Int = when (pictureNo % 4) {
            1 -> R.drawable.defender
            2 -> R.drawable.midfield
            3 -> R.drawable.striker
            else -> R.drawable.keeper
        }

        val stats: Int = stats.toString().take(2).toInt()

        return Pair(picture, stats)
    }

}