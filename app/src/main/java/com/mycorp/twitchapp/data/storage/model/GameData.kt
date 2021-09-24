package com.mycorp.twitchapp.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameData(
    @PrimaryKey
    val id: Int,
    val name: String,
    val logoUrl: String,
    val channelsCount:Int,
    val watchersCount:Int
)