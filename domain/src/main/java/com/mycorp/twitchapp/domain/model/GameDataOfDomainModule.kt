package com.mycorp.twitchapp.domain.model

data class GameDataOfDomainModule(
    val id:Int,
    val name: String,
    val logoUrl: String,
    val channelsCount: Int,
    val watchersCount: Int,
)
