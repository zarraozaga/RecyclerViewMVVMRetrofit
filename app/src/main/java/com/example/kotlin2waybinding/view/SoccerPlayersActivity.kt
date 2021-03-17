package com.example.kotlin2waybinding.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin2waybinding.R
import com.example.kotlin2waybinding.model.Player
import com.example.kotlin2waybinding.viewmodel.SoccerPlayerViewModel

class SoccerPlayersActivity : AppCompatActivity() {

    lateinit var soccerPlayerVM: SoccerPlayerViewModel
    lateinit var soccerPlayersAdapter: SoccerPlayersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soccer_players)

        soccerPlayerVM = ViewModelProvider(this)[SoccerPlayerViewModel::class.java]
        soccerPlayersAdapter = SoccerPlayersAdapter()

        var soccerPlayersRecyclerView : RecyclerView = findViewById(R.id.soccer_players_rv)

        soccerPlayersRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = soccerPlayersAdapter
        }

        soccerPlayerVM.listPlayers()
        observeSoccerPlayers()
    }

    fun observeSoccerPlayers() {
        soccerPlayerVM.playersLive.observe(this, Observer<List<Player>?> { players ->
            players?.let { soccerPlayersAdapter.updateSoccerPlayerList(players) }
        })
    }

}