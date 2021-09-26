package com.mycorp.twitchapp.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycorp.twitchapp.databinding.ActivityMainBinding
import com.mycorp.twitchapp.domain.model.GameDataOfDomainModule
import com.mycorp.twitchapp.domain.model.Status
import kotlin.collections.ArrayList

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
        subscribeByGamesFromDb()
        subscribeByGamesFromNetwork()
        viewModel = ViewModelProvider(this, MainViewModelFactory(this))[MainViewModel::class.java]

    }

    private fun subscribeByGamesFromDb(){
        viewModel.getGamesFromDb().observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        retrieveList(resource.data as ArrayList<GameDataOfDomainModule>)
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        Toast.makeText(this, "LOADING", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun subscribeByGamesFromNetwork(){
        viewModel.getGamesFromNetwork().observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        retrieveList(resource.data as ArrayList<GameDataOfDomainModule>)
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        Toast.makeText(this, "LOADING", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun retrieveList(games: ArrayList<GameDataOfDomainModule>) {
        gamesListAdapter.apply {
            addGames(games)
            notifyDataSetChanged()
        }
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