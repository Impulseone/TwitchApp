package com.mycorp.twitchapp.domain.use_cases

import com.mycorp.twitchapp.domain.model.GameDataOfDomainModule
import com.mycorp.twitchapp.domain.repository.Repository
import java.util.ArrayList

class GetFromDbUseCase(private val repository: Repository) {
   suspend fun execute():ArrayList<GameDataOfDomainModule> = repository.getGamesDataFromDb()
}