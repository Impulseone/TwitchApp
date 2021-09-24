package com.mycorp.twitchapp.domain.use_cases

import com.mycorp.twitchapp.domain.repository.Repository

class GetFromNetworkUseCase(private val repository: Repository) {
    fun execute() = repository.getGamesDataFromNetwork()
}