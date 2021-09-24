package com.mycorp.twitchapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mycorp.twitchapp.databinding.ActivityRatingBinding
import android.widget.Toast

import android.widget.RatingBar.OnRatingBarChangeListener


class RatingActivity : AppCompatActivity() {

    private lateinit var ratingActivityBinding: ActivityRatingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ratingActivityBinding = ActivityRatingBinding.inflate(layoutInflater)
        setContentView(ratingActivityBinding.root)

        ratingActivityBinding.ratingBarDefault.onRatingBarChangeListener =
            OnRatingBarChangeListener { _, rating, _ ->
                Toast.makeText(
                    this@RatingActivity, "рейтинг: $rating",
                    Toast.LENGTH_LONG
                ).show()
            }

        ratingActivityBinding.sendReportBtn.setOnClickListener{startActivity(Intent(this,
            MainActivity::class.java))}
        ratingActivityBinding.backBtn.setOnClickListener{startActivity(Intent(this, MainActivity::class.java))}
    }
}