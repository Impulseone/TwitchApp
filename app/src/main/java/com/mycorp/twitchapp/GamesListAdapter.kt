package com.mycorp.twitchapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycorp.twitchapp.databinding.GameItemViewBinding
import com.mycorp.twitchapp.retrofit.TopItem
import com.squareup.picasso.Picasso

class GamesListAdapter(private val items: List<TopItem?>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ItemView(
            LayoutInflater.from(parent.context).inflate(R.layout.game_item_view, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemView).bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(topItem: TopItem?) {
            GameItemViewBinding.bind(itemView).apply {
                Picasso.get().load(topItem?.game?.box?.large).into(image)
                gameName.text = "Game: " + topItem?.game?.name
                channelsCount.text = "Channels: " + topItem?.channels.toString()
                watchersCount.text = "Viewers: " + topItem?.viewers.toString()
            }
        }
    }
}