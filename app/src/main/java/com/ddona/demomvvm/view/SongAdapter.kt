package com.ddona.demomvvm.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddona.demomvvm.databinding.ItemSongBinding
import com.ddona.demomvvm.model.ItemSong
import com.ddona.demomvvm.viewmodel.SongViewModel

class SongAdapter : RecyclerView.Adapter<SongAdapter.SongHolder> {
    private val inter: ISongAdapter

    constructor(inter: ISongAdapter) {
        this.inter = inter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        return SongHolder(
            ItemSongBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount()= inter.getCount()

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.binding.data = inter.getData(position)
        holder.binding.root.setOnClickListener({
            inter.onClickItem(holder.adapterPosition)
        })
    }

    interface ISongAdapter {
        fun getCount(): Int
        fun getData(position: Int): ItemSong
        fun onClickItem(position: Int)
    }

    class SongHolder(val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root)

}