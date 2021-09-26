package com.mycorp.twitchapp.data.network

import android.annotation.SuppressLint
import com.mycorp.twitchapp.data.storage.model.GameData
import com.mycorp.twitchapp.data.storage.retrofit.Common
import com.mycorp.twitchapp.data.storage.retrofit.TopItem
import com.mycorp.twitchapp.data.storage.retrofit.TwitchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkControllerImpl : NetworkController {
    override suspend fun getDataFromNetwork(): List<GameData> {
        return convertItemsToGamesData(Common.retrofitService.loadGames().top!!)
    }

    private fun convertItemsToGamesData(items: List<TopItem?>): List<GameData> {
        val gamesData: MutableList<GameData> = mutableListOf()
        for (item in items) {
            gamesData.add(
                GameData(
                    item?.game?.id!!,
                    item.game.name!!,
                    item.game.box?.large!!,
                    item.channels!!,
                    item.viewers!!
                )
            )
        }
        return gamesData
    }
}