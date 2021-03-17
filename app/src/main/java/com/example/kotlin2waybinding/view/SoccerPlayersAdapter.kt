package com.example.kotlin2waybinding.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin2waybinding.R
import com.example.kotlin2waybinding.model.Player
import kotlin.properties.Delegates

class SoccerPlayersAdapter: RecyclerView.Adapter<SoccerPlayersAdapter.SoccerPlayerViewHolder>() {

    private var soccerPlayerList: List<Player> by Delegates.observable(emptyList()){ _, _, _ ->
        notifyDataSetChanged()
    }

    fun updateSoccerPlayerList(players: List<Player>){
        this.soccerPlayerList = players
    }


    class SoccerPlayerViewHolder(playerView: View) : RecyclerView.ViewHolder(playerView){
        val playerImage : ImageView = playerView.findViewById(R.id.playerImage)
        val playerStats : TextView = playerView.findViewById(R.id.playerStats)
        val playerName : TextView = playerView.findViewById(R.id.playerName)

        fun bind(playerModel : Player){
            playerImage.setImageResource(playerModel.picture)
            playerName.text = playerModel.name
            playerStats.text = playerModel.stats.toString()
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoccerPlayerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.player_view,parent,false)
        val holder = SoccerPlayerViewHolder(view)

        return holder
    }

    override fun onBindViewHolder(holder: SoccerPlayerViewHolder, position: Int) {
        holder.bind(soccerPlayerList[position])
    }

    override fun getItemCount(): Int {
        return soccerPlayerList.size
    }

}