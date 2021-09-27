package com.mycorp.twitchapp.data.repository

import com.mycorp.twitchapp.data.network.NetworkController
import com.mycorp.twitchapp.data.storage.model.GameData
import com.mycorp.twitchapp.data.storage.Storage
import com.mycorp.twitchapp.domain.model.GameDataOfDomainModule
import com.mycorp.twitchapp.domain.repository.Repository
import java.util.ArrayList

class RepositoryImplementation(
    private val storage: Storage,
    private val networkController: NetworkController
) : Repository {
    override suspend fun getGamesDataFromDb() =
        convertGameDataToGameDataOfDomainModel(storage.getGamesDataFromDb())

    override suspend fun getGamesDataFromNetwork():ArrayList<GameDataOfDomainModule> {
        val gamesData = convertGameDataToGameDataOfDomainModel(networkController.getDataFromNetwork() as ArrayList<GameData>)
        insertGamesData(gamesData)
        return gamesData
    }

    override suspend fun insertGamesData(gamesData: List<GameDataOfDomainModule>) =
        storage.insertGamesData(convertGameDataOfDomainModelToGameData(gamesData))

    private fun convertGameDataToGameDataOfDomainModel(gamesData: List<GameData>): ArrayList<GameDataOfDomainModule> {
        val gamesDataOfDomainModule = ArrayList<GameDataOfDomainModule>()
        for (game in gamesData) {
            gamesDataOfDomainModule.add(
                GameDataOfDomainModule(
                    game.id,
                    game.name,
                    game.logoUrl,
                    game.channelsCount,
                    game.watchersCount
                )
            )
        }
        return gamesDataOfDomainModule
    }

    private fun convertGameDataOfDomainModelToGameData(gamesDataOfDomainModule: List<GameDataOfDomainModule>): ArrayList<GameData> {
        val gamesData = ArrayList<GameData>()
        for (game in gamesDataOfDomainModule) {
            gamesData.add(
                GameData(
                    game.id,
                    game.name,
                    game.logoUrl,
                    game.channelsCount,
                    game.watchersCount
                )
            )
        }
        return gamesData
    }
}