package com.mycorp.twitchapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mycorp.twitchapp.domain.model.GameDataOfDomainModule
import com.mycorp.twitchapp.domain.model.Resource
import com.mycorp.twitchapp.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapp.domain.use_cases.GetFromNetworkUseCase
import com.mycorp.twitchapp.domain.use_cases.InsertToDbUseCase
import kotlinx.coroutines.Dispatchers

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

    fun getGamesFromNetwork() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = getFromNetworkUseCase.execute()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}