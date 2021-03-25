package com.example.kotlin2waybinding.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin2waybinding.R
import com.example.kotlin2waybinding.model.Player
import com.example.kotlin2waybinding.viewmodel.SoccerPlayerViewModel
import com.example.kotlin2waybinding.databinding.FragmentPlayerListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [PlayerListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayerListFragment : Fragment() {

    private lateinit var binding: FragmentPlayerListBinding
    lateinit var soccerPlayerVM: SoccerPlayerViewModel
    lateinit var soccerPlayersAdapter: SoccerPlayersAdapter
    protected lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_player_list,
            container,
            false
        )

        soccerPlayerVM = ViewModelProvider(this)[SoccerPlayerViewModel::class.java]

        binding.soccerPlayerVM = soccerPlayerVM

        val adapter = SoccerPlayersAdapter(SoccerPlayerListener { playerId ->
            Toast.makeText(context, "${playerId}", Toast.LENGTH_SHORT).show()
            soccerPlayerVM.playerDetailsClicked(playerId)
        })

        binding.soccerPlayersRvFragment.adapter = adapter


        binding.setLifecycleOwner(this)

        soccerPlayerVM.listPlayers()

        soccerPlayerVM.navigateToPlayerDetails.observe(this, Observer { player_id ->
            player_id?.let {
                this.findNavController().navigate(
                        PlayerListFragmentDirections
                            .actionPlayerListFragmentToPlayerDetailsFragment(player_id))
                soccerPlayerVM.onPlayerDetailsNavigated()
            }
        })



        //val manager = GridLayoutManager(activity, 1)
        binding.soccerPlayersRvFragment.layoutManager = LinearLayoutManager(activity)

        observeSoccerPlayers(adapter)


        return binding.root
    }

    fun observeSoccerPlayers(adapter : SoccerPlayersAdapter) {
        soccerPlayerVM.playersLive.observe(this, Observer<List<Player>?> { players ->
            players?.let { adapter.updateSoccerPlayerList(players) }
        })
    }

}