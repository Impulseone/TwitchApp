package com.mycorp.twitchapp.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycorp.twitchapp.data.repository.RepositoryImplementation
import com.mycorp.twitchapp.data.storage.room.AppDatabase
import com.mycorp.twitchapp.data.storage.model.GameData
import com.mycorp.twitchapp.databinding.ActivityMainBinding
import com.mycorp.twitchapp.data.storage.retrofit.Common
import com.mycorp.twitchapp.data.storage.retrofit.TopItem
import com.mycorp.twitchapp.data.storage.retrofit.TwitchResponse
import com.mycorp.twitchapp.data.storage.room.RoomStorage
import com.mycorp.twitchapp.domain.use_cases.GetFromDbUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private val getFromDbUseCase by lazy { GetFromDbUseCase(RepositoryImplementation(RoomStorage(applicationContext))) }

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var gamesListAdapter: GamesListAdapter

    private var games: ArrayList<GameData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        gamesListAdapter = GamesListAdapter(games)
        setRvAdapter()
        initReportButton()
        getGamesFromDb()
//        getGamesFromApi()
    }

//    private fun getGamesFromApi() {
//        Common.retrofitService.loadGames().enqueue(object : Callback<TwitchResponse> {
//            override fun onFailure(call: Call<TwitchResponse>, t: Throwable) {
//                t.printStackTrace()
//            }
//
//            @SuppressLint("NotifyDataSetChanged")
//            override fun onResponse(
//                call: Call<TwitchResponse>,
//                response: Response<TwitchResponse>
//            ) {
//                val twitchResponse = response.body()
//                if (twitchResponse != null) twitchResponse.top?.let {
//                    if (it.isNotEmpty()) {
//                        insertGameDataToDb(it)
//                        games.addAll(convertItemsToGames(it))
//                        gamesListAdapter.notifyDataSetChanged()
//                    }
//                }
//
//            }
//        })
//    }

//    private fun insertGameDataToDb(items: List<TopItem?>) {
//        for (item in items) {
//            db.gameDataDao.insert(
//                GameData(
//                    item?.game?.id!!,
//                    item.game.name!!,
//                    item.game.box?.large!!,
//                    item.channels!!,
//                    item.viewers!!
//                )
//            )
//        }
//    }

    private fun convertItemsToGames(items: List<TopItem?>): List<GameData> {
        val gamesData: MutableList<GameData> = mutableListOf()
        for (item in items) {
            gamesData.add(
                GameData(
                    item?.game?.id!!,
                    item.game.name!!,
                    item.game.box?.large!!,
                    item.channels!!,
                    item.viewers!!
                )
            )
        }
        return gamesData
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