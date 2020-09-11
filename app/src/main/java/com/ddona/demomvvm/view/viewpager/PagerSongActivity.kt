package com.ddona.demomvvm.view.viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ddona.demomvvm.R
import com.ddona.demomvvm.databinding.ActivityPagerSongBinding

class PagerSongActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPagerSongBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_pager_song
        )
        binding.tab.setupWithViewPager(
            binding.vp
        )
        binding.vp.adapter = SongAdapter(supportFragmentManager)
    }
}