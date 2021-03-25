package com.example.kotlin2waybinding.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin2waybinding.R
import com.example.kotlin2waybinding.databinding.PlayerViewBinding
import com.example.kotlin2waybinding.model.Player
import kotlin.properties.Delegates

class SoccerPlayersAdapter(val clickListener: SoccerPlayerListener): RecyclerView.Adapter<SoccerPlayersAdapter.SoccerPlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SoccerPlayerViewHolder(
            DataBindingUtil.inflate<PlayerViewBinding>
                (
                LayoutInflater.from(parent.context),
                R.layout.player_view,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SoccerPlayerViewHolder, position: Int) {
        holder.bind(soccerPlayerList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return soccerPlayerList.size
    }

    private var soccerPlayerList: List<Player> by Delegates.observable(emptyList()){ _, _, _ ->
        notifyDataSetChanged()
    }

    fun updateSoccerPlayerList(players: List<Player>){
        this.soccerPlayerList = players
    }

    class SoccerPlayerViewHolder (val binding: PlayerViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(playerModel: Player, clickListener: SoccerPlayerListener){
            Log.i("ITS HERE", playerModel.toString())
            binding.player = playerModel
            binding.clickListener = clickListener
        }
    }

}

class SoccerPlayerListener(val clickListener: (playerId: Long) -> Unit) {
    fun onClick(player: Player) = clickListener(player.id)
}