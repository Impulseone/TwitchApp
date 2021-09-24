package com.mycorp.twitchapp.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycorp.twitchapp.data.network.NetworkControllerImpl
import com.mycorp.twitchapp.data.repository.RepositoryImplementation
import com.mycorp.twitchapp.data.storage.model.GameData
import com.mycorp.twitchapp.data.storage.room.RoomStorage
import com.mycorp.twitchapp.databinding.ActivityMainBinding
import com.mycorp.twitchapp.domain.model.GameDataOfDomainModule
import com.mycorp.twitchapp.domain.use_cases.GetFromDbUseCase
import com.mycorp.twitchapp.domain.use_cases.GetFromNetworkUseCase
import com.mycorp.twitchapp.domain.use_cases.InsertToDbUseCase
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var gamesListAdapter: GamesListAdapter
    private var games: ArrayList<GameDataOfDomainModule> = arrayListOf()
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        gamesListAdapter = GamesListAdapter(games)
        setRvAdapter()
        initReportButton()

        viewModel = ViewModelProvider(this, MainViewModelFactory(this))[MainViewModel::class.java]
        viewModel.getFromDbLiveData.observe(this,{
            games.addAll(it)
            gamesListAdapter.notifyDataSetChanged()
        })
        viewModel.getGamesFromDb()
    }

    private fun setRvAdapter() {
        activityMainBinding.gamesRv.layoutManager =
            LinearLayoutManager(this)
        activityMainBinding.gamesRv.adapter = gamesListAdapter
    }

    private fun initReportButton() {
        activityMainBinding.reportButton.setOnClickListener {
            startActivity(Intent(this, RatingActivity::class.java))
        }
    }
}