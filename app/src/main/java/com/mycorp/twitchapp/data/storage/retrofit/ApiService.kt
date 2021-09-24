package com.mycorp.twitchapp.data.storage.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @Headers("Accept: application/vnd.twitchtv.v5+json", "Client-ID: ahuoi1tl0qmqbyi8jo8nitbmuaad7w")
    @GET("kraken/games/top")
    fun loadGames(): Call<TwitchResponse>
}