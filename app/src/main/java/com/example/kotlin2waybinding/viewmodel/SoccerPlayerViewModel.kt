package com.example.kotlin2waybinding.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin2waybinding.R
import com.example.kotlin2waybinding.api.RetrofitBuilder
import com.example.kotlin2waybinding.model.Player
import com.example.kotlin2waybinding.repository.PlayerRepository
import com.example.kotlin2waybinding.usecase.PlayerDetailsManipulationImpl
import kotlinx.coroutines.launch

class SoccerPlayerViewModel() : ViewModel() {
    private val playerRepo:PlayerRepository = PlayerRepository(RetrofitBuilder.apiService)
    val playersLive = MutableLiveData<List<Player>>()

    fun listPlayers() {
        viewModelScope.launch {
            val playerfitiml = PlayerDetailsManipulationImpl(playerRepo)
            val lp = playerfitiml.getPlayersList()
            playersLive.value = lp
        }
    }

}