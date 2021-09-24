package com.mycorp.twitchapp.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

    private val getFromDbUseCase by lazy {
        GetFromDbUseCase(
            RepositoryImplementation(
                RoomStorage(
                    applicationContext
                ), NetworkControllerImpl()
            )
        )
    }
    private val insertToDbUseCase by lazy {
        InsertToDbUseCase(
            RepositoryImplementation(
                RoomStorage(
                    applicationContext
                ), NetworkControllerImpl()
            )
        )
    }
    private val getFromNetworkUseCase by lazy {
        GetFromNetworkUseCase(
            RepositoryImplementation(
                RoomStorage(applicationContext),
                NetworkControllerImpl()
            )
        )
    }

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var gamesListAdapter: GamesListAdapter

    private var games: ArrayList<GameDataOfDomainModule> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        gamesListAdapter = GamesListAdapter(games)
        setRvAdapter()
        initReportButton()
//        getGamesFromDb()
        getGamesFromApi()
    }

    private fun getGamesFromApi() {
        games = getFromNetworkUseCase.execute()
//        gamesListAdapter.notifyDataSetChanged()
    }

    private fun insertGameDataToDb(gamesData: List<GameDataOfDomainModule>) {
        insertToDbUseCase.execute(gamesData)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getGamesFromDb() {
        val gamesFromDb = getFromDbUseCase.execute()
        games.addAll(gamesFromDb)
        gamesListAdapter.notifyDataSetChanged()
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