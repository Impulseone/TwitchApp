package com.mycorp.twitchapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mycorp.twitchapp.domain.model.Resource
import com.mycorp.twitchapp.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapp.domain.use_cases.GetFromNetworkUseCase
import kotlinx.coroutines.Dispatchers

class MainViewModel(
    private val getFromDbUseCase: GetFromDbUseCase,
    private val getFromNetworkUseCase: GetFromNetworkUseCase
) : ViewModel() {

    fun getGamesFromDb() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = getFromDbUseCase.execute()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
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