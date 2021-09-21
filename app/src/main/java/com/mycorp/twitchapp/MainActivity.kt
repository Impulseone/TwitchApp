package com.mycorp.twitchapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mycorp.twitchapp.databinding.ActivityMainBinding
import com.mycorp.twitchapp.retrofit.Common
import com.mycorp.twitchapp.retrofit.TwitchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var gamesListAdapter: GamesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        Common.retrofitService.loadGames().enqueue(object : Callback<TwitchResponse> {
            override fun onFailure(call: Call<TwitchResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<TwitchResponse>,
                response: Response<TwitchResponse>
            ) {
                val twitchResponse = response.body()
                if (twitchResponse != null) twitchResponse.top?.let {
                    gamesListAdapter = GamesListAdapter(it)
                    activityMainBinding.gamesRv.layoutManager = LinearLayoutManager(this@MainActivity)
                    activityMainBinding.gamesRv.adapter = gamesListAdapter
                }

            }
        })
    }
}