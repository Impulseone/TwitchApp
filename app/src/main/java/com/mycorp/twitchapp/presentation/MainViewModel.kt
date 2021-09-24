package com.mycorp.twitchapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycorp.twitchapp.domain.model.GameDataOfDomainModule
import com.mycorp.twitchapp.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapp.domain.use_cases.GetFromNetworkUseCase
import com.mycorp.twitchapp.domain.use_cases.InsertToDbUseCase

class MainViewModel(
    private val getFromDbUseCase: GetFromDbUseCase,
    private val insertToDbUseCase: InsertToDbUseCase,
    private val getFromNetworkUseCase: GetFromNetworkUseCase
) : ViewModel() {
    private val getFromDbLiveDataMutable = MutableLiveData<List<GameDataOfDomainModule>>()
    val getFromDbLiveData = getFromDbLiveDataMutable

    fun getGamesFromDb() {
        getFromDbLiveDataMutable.value = getFromDbUseCase.execute()
    }
}