package com.mycorp.twitchapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycorp.twitchapp.database.AppDatabase
import com.mycorp.twitchapp.database.GameData
import com.mycorp.twitchapp.databinding.ActivityMainBinding
import com.mycorp.twitchapp.retrofit.Common
import com.mycorp.twitchapp.retrofit.Game
import com.mycorp.twitchapp.retrofit.TopItem
import com.mycorp.twitchapp.retrofit.TwitchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var gamesListAdapter: GamesListAdapter
    private lateinit var db: AppDatabase
    private var games: ArrayList<GameData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        gamesListAdapter = GamesListAdapter(games)
        setRvAdapter()
        db = AppDatabase.getInstance(this)
        getGamesFromDb()
        getGamesFromApi()
    }

    private fun getGamesFromApi() {
        Common.retrofitService.loadGames().enqueue(object : Callback<TwitchResponse> {
            override fun onFailure(call: Call<TwitchResponse>, t: Throwable) {
                t.printStackTrace()
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<TwitchResponse>,
                response: Response<TwitchResponse>
            ) {
                val twitchResponse = response.body()
                if (twitchResponse != null) twitchResponse.top?.let {
                    if (it.isNotEmpty()) {
                        insertGameDataToDb(it)
                        games.addAll(convertItemsToGames(it))
                        gamesListAdapter.notifyDataSetChanged()
                    }
                }

            }
        })
    }

    private fun insertGameDataToDb(items: List<TopItem?>) {
        for (item in items) {
            db.gameDataDao.insert(
                GameData(
                    item?.game?.id!!,
                    item.game.name!!,
                    item.game.box?.large!!,
                    item.channels!!,
                    item.viewers!!
                )
            )
        }
    }

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
        val gamesFromDb = db.gameDataDao.getAllGames()
        games.addAll(gamesFromDb)
        gamesListAdapter.notifyDataSetChanged()
    }

    private fun setRvAdapter(){
        activityMainBinding.gamesRv.layoutManager =
            LinearLayoutManager(this)
        activityMainBinding.gamesRv.adapter = gamesListAdapter
    }
}