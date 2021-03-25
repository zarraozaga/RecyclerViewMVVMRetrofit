package com.example.kotlin2waybinding.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.kotlin2waybinding.R
import com.example.kotlin2waybinding.databinding.FragmentPlayerDetailsBinding
import com.example.kotlin2waybinding.model.Player
import com.example.kotlin2waybinding.viewmodel.SoccerPlayerViewModel

class PlayerDetailsFragment : Fragment() {

    lateinit var soccerPlayerVM: SoccerPlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentPlayerDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_player_details, container, false)

        soccerPlayerVM = ViewModelProvider(this)[SoccerPlayerViewModel::class.java]

        val application = requireNotNull(this.activity).application
        val arguments : PlayerDetailsFragmentArgs by navArgs()
        soccerPlayerVM.getPlayer(arguments.playerId)

        soccerPlayerVM.playerDetails.observe(this, Observer<Player?> { player ->
            player.let {
                binding.player = player
            }
        })

        soccerPlayerVM.loading.observe(this, Observer<Boolean> { it ->
            it.let {
                binding.loading = it
            }
        })

        return binding.root
    }


}