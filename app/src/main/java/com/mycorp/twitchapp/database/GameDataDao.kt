package com.mycorp.twitchapp.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface GameDataDao {
    @Insert(onConflict = REPLACE)
    fun insert(gameData: GameData)

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg gameData: GameData)

    @Update
    fun update(gameData: GameData)

    @Delete
    fun delete(gameData: GameData)

    @Query("SELECT * FROM GameData")
    fun getAllGames(): List<GameData>

    @Query("SELECT * FROM GameData WHERE id=(:id)")
    fun getGameById(id: Int): GameData
}