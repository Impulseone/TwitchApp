package com.mycorp.twitchapp.domain.use_cases

import com.mycorp.twitchapp.domain.model.GameDataOfDomainModule
import com.mycorp.twitchapp.domain.repository.Repository

class InsertToDbUseCase (private val repository: Repository) {
   suspend fun execute(gamesData: List<GameDataOfDomainModule>){
        repository.insertGamesData(gamesData)
    }
}