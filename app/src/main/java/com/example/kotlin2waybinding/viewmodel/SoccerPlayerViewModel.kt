package com.example.kotlin2waybinding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin2waybinding.R
import com.example.kotlin2waybinding.model.Player

class SoccerPlayerViewModel : ViewModel() {

    val playersLive = MutableLiveData<List<Player>>()

    fun listPlayers() {
        val lp: List<Player> = listOf(Player("Gerrard", 2, 99), Player("Torres", 3, 95))
        for (i in lp) {
            i.picture = when (i.picture % 4) {
                1 -> R.drawable.defender
                2 -> R.drawable.midfield
                3 -> R.drawable.striker
                else -> R.drawable.keeper
            }
        }

        playersLive.value = lp

    }

}