package com.example.kotlin2waybinding.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin2waybinding.R
import com.example.kotlin2waybinding.model.Player
import com.example.kotlin2waybinding.viewmodel.SoccerPlayerViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [PlayerListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayerListFragment : Fragment() {

    lateinit var soccerPlayerVM: SoccerPlayerViewModel
    lateinit var soccerPlayersAdapter: SoccerPlayersAdapter
    protected lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        rootView =  inflater.inflate(R.layout.fragment_player_list, container, false)
        soccerPlayerVM = ViewModelProvider(this)[SoccerPlayerViewModel::class.java]
        soccerPlayersAdapter = SoccerPlayersAdapter()

        var soccerPlayersRecyclerView : RecyclerView = rootView.findViewById(R.id.soccer_players_rv_fragment)

        soccerPlayersRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = soccerPlayersAdapter
        }

        soccerPlayerVM.listPlayers()
        observeSoccerPlayers()

        return rootView
    }

    fun observeSoccerPlayers() {
        soccerPlayerVM.playersLive.observe(this, Observer<List<Player>?> { players ->
            players?.let { soccerPlayersAdapter.updateSoccerPlayerList(players) }
        })
    }

}