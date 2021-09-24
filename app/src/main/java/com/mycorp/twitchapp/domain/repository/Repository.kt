package com.mycorp.twitchapp.domain.repository

import com.mycorp.twitchapp.data.storage.model.GameData
import java.util.ArrayList

interface Repository {
    fun getGamesData():ArrayList<GameData>
    fun insertGamesData(gamesData: List<GameData>)
}