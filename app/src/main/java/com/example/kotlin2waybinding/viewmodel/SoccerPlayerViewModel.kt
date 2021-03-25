package com.example.kotlin2waybinding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin2waybinding.api.RetrofitBuilder
import com.example.kotlin2waybinding.model.Player
import com.example.kotlin2waybinding.repository.PlayerRepository
import com.example.kotlin2waybinding.usecase.PlayerDetailsManipulationImpl
import kotlinx.coroutines.launch

class SoccerPlayerViewModel() : ViewModel() {
    private val _playerRepo:PlayerRepository = PlayerRepository(RetrofitBuilder.mockDataAPIService, RetrofitBuilder.metaphorsumAPIService)
    private val _navigateToPlayerDetails = MutableLiveData<Long>()


    val playersLive = MutableLiveData<List<Player>>()
    val playerDetails = MutableLiveData<Player>()
    val navigateToPlayerDetails get() = _navigateToPlayerDetails
    val loading : MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun listPlayers() {
        loading.value = true
        viewModelScope.launch {
            val playerfitiml = PlayerDetailsManipulationImpl(_playerRepo)
            val lp = playerfitiml.getPlayersList()
            playersLive.value = lp
            loading.value = false
        }
    }

    fun getPlayer(id: Long) {
        loading.value = true
        viewModelScope.launch {
            val playerfitiml = PlayerDetailsManipulationImpl(_playerRepo)
            val lp = playerfitiml.getPlayer(id)
            playerDetails.value = lp
            loading.value = false
        }
    }

    fun playerDetailsClicked(id: Long) {
        _navigateToPlayerDetails.value = id
    }

    fun onPlayerDetailsNavigated() {
        _navigateToPlayerDetails.value = null
    }




}