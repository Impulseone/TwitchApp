package com.mycorp.twitchapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycorp.twitchapp.databinding.ActivityMainBinding
import com.mycorp.twitchapp.retrofit.Common
import com.mycorp.twitchapp.retrofit.TwitchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        Common.retrofitService.loadGames().enqueue(object: Callback<TwitchResponse> {
            override fun onFailure(call: Call<TwitchResponse>, t: Throwable) {
                print("error")
            }

            override fun onResponse(
                call: Call<TwitchResponse>,
                response: Response<TwitchResponse>
            ) {
                var twitchResponse = response.body()
                print("all is fine")
            }
        })
//        activityMainBinding.button.setOnClickListener{
//
//        }
    }
}