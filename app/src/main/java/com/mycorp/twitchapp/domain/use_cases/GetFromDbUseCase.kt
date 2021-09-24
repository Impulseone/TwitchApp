package com.mycorp.twitchapp.domain.use_cases

import com.mycorp.twitchapp.data.storage.model.GameData
import com.mycorp.twitchapp.domain.repository.Repository
import java.util.ArrayList

class GetFromDbUseCase(private val repository: Repository) {
    fun execute():ArrayList<GameData> = repository.getGamesData()
}