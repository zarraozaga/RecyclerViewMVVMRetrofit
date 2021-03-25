package com.example.kotlin2waybinding.model

data class Player
(
        var id: Long = 0L,
        var name: String,
        var picture: Int,
        var stats: Int,
        var details : String?
)
