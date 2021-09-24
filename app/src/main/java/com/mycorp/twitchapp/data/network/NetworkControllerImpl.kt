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
    override fun getDataFromNetwork(): List<GameData> {
        val games = ArrayList<GameData>()
        Common.retrofitService.loadGames().enqueue(object : Callback<TwitchResponse> {
            override fun onFailure(call: Call<TwitchResponse>, t: Throwable) {
                t.printStackTrace()
            }
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<TwitchResponse>,
                response: Response<TwitchResponse>
            ) {
                val twitchResponse = response.body()
                if (twitchResponse != null) twitchResponse.top?.let {
                    if (it.isNotEmpty()) {
                        games.addAll(convertItemsToGames(it))
                    }
                }
            }
        })
        return games
    }

    private fun convertItemsToGames(items: List<TopItem?>): List<GameData> {
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