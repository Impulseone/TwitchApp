package com.mycorp.twitchapp.data.repository

import com.mycorp.twitchapp.data.network.NetworkController
import com.mycorp.twitchapp.data.storage.model.GameData
import com.mycorp.twitchapp.data.storage.Storage
import com.mycorp.twitchapp.domain.repository.Repository
import java.util.ArrayList

class RepositoryImplementation(private val storage: Storage, private val networkController: NetworkController) : Repository {
    override fun getGamesDataFromDb() = storage.getGamesDataFromDb()
    override fun getGamesDataFromNetwork() = networkController.getDataFromNetwork() as ArrayList<GameData>
    override fun insertGamesData(gamesData: List<GameData>) = storage.insertGamesData(gamesData)
}