package com.mycorp.twitchapp.data.storage

import com.mycorp.twitchapp.data.storage.model.GameData
import java.util.ArrayList

interface Storage {
    fun getGamesData(): ArrayList<GameData>
    fun insertGamesData(gamesData: List<GameData>)
}