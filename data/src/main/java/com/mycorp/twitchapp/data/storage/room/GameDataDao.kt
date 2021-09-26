package com.mycorp.twitchapp.data.storage.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.mycorp.twitchapp.data.storage.model.GameData

@Dao
interface GameDataDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(gameData: GameData)

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(objects: List<GameData>)

    @Update
    suspend fun update(gameData: GameData)

    @Delete
    suspend fun delete(gameData: GameData)

    @Query("SELECT * FROM GameData")
    suspend fun getAllGames(): List<GameData>

    @Query("SELECT * FROM GameData WHERE id=(:id)")
    suspend fun getGameById(id: Int): GameData
}