package com.mycorp.twitchapp.domain.repository

import com.mycorp.twitchapp.domain.model.GameDataOfDomainModule
import java.util.*

interface Repository {
    suspend fun getGamesDataFromDb(): ArrayList<GameDataOfDomainModule>
    suspend fun getGamesDataFromNetwork(): ArrayList<GameDataOfDomainModule>
    suspend fun insertGamesData(gamesData: List<GameDataOfDomainModule>)
}