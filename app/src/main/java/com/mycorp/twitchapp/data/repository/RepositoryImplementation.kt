package com.mycorp.twitchapp.data.repository

import com.mycorp.twitchapp.data.storage.model.GameData
import com.mycorp.twitchapp.data.storage.Storage
import com.mycorp.twitchapp.domain.repository.Repository
import java.util.ArrayList

class RepositoryImplementation(private val storage: Storage) : Repository {
    override fun getGamesData(): ArrayList<GameData> = storage.getGamesData()
}