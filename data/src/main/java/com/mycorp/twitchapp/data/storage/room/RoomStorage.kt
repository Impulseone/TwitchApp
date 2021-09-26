package com.mycorp.twitchapp.data.storage.room

import android.content.Context
import com.mycorp.twitchapp.data.storage.Storage
import com.mycorp.twitchapp.data.storage.model.GameData
import java.util.ArrayList

class RoomStorage(private val context: Context): Storage {

    private var db:AppDatabase = AppDatabase.getInstance(context = context)

    override suspend fun getGamesDataFromDb(): ArrayList<GameData> = db.gameDataDao.getAllGames() as ArrayList<GameData>
    override suspend fun insertGamesData(gamesData: List<GameData>){
        db.gameDataDao.insertAll(gamesData)
    }

}