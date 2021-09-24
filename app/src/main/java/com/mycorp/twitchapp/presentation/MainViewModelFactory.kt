package com.mycorp.twitchapp.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mycorp.twitchapp.data.network.NetworkControllerImpl
import com.mycorp.twitchapp.data.repository.RepositoryImplementation
import com.mycorp.twitchapp.data.storage.room.RoomStorage
import com.mycorp.twitchapp.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapp.domain.use_cases.GetFromNetworkUseCase
import com.mycorp.twitchapp.domain.use_cases.InsertToDbUseCase

class MainViewModelFactory (context: Context):ViewModelProvider.Factory {

    private val getFromDbUseCase by lazy {
        GetFromDbUseCase(
            RepositoryImplementation(
                RoomStorage(
                    context
                ), NetworkControllerImpl()
            )
        )
    }
    private val insertToDbUseCase by lazy {
        InsertToDbUseCase(
            RepositoryImplementation(
                RoomStorage(
                    context
                ), NetworkControllerImpl()
            )
        )
    }
    private val getFromNetworkUseCase by lazy {
        GetFromNetworkUseCase(
            RepositoryImplementation(
                RoomStorage(context),
                NetworkControllerImpl()
            )
        )
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getFromDbUseCase, insertToDbUseCase, getFromNetworkUseCase) as T
    }
}