package com.mycorp.twitchapp.data.storage

import com.mycorp.twitchapp.data.storage.model.GameData
import java.util.ArrayList

interface Storage {
   suspend fun getGamesDataFromDb(): ArrayList<GameData>
   suspend fun insertGamesData(gamesData: List<GameData>)
}