package com.mycorp.twitchapp.data.network

import com.mycorp.twitchapp.data.storage.model.GameData

interface NetworkController {
    fun getDataFromNetwork():List<GameData>
}