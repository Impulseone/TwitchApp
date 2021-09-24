package com.mycorp.twitchapp.domain.use_cases

import com.mycorp.twitchapp.data.storage.model.GameData
import com.mycorp.twitchapp.domain.repository.Repository

class InsertToDbUseCase (private val repository: Repository) {
    fun execute(gamesData: List<GameData>){
        repository.insertGamesData(gamesData)
    }
}