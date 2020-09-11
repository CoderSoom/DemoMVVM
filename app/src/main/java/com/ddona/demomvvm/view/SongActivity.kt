package com.ddona.demomvvm.view

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddona.demomvvm.R
import com.ddona.demomvvm.databinding.ActivitySongBinding
import com.ddona.demomvvm.viewmodel.SongViewModel
import java.text.SimpleDateFormat
import java.util.concurrent.Executors

class SongActivity : AppCompatActivity(), View.OnClickListener, SongAdapter.ISongAdapter,
    MusicOnline.IMusicOnline {
    private lateinit var model: SongViewModel
    private lateinit var binding: ActivitySongBinding
    private lateinit var musicOnline: MusicOnline
    private var asyPlay: MyAsyn?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_song
        )
        musicOnline = MusicOnline()
        musicOnline.inter = this

        model = SongViewModel()
        binding.data = model



        binding.rc.layoutManager = LinearLayoutManager(this)
        binding.rc.adapter = SongAdapter(this)
        register()
        binding.btnSearch.setOnClickListener(this)


    }

    private fun register() {
        model.songData.observe(this, Observer {
            Log.d("MainActivity", "observe register...................")
            binding.rc.adapter!!.notifyDataSetChanged()

        })

        model.linkSong.observe(this, Observer {
            Log.d("MainActivity", "observe link song: " + it.link)
            musicOnline.release()
            musicOnline.setDataSource(this, it.link)
        })
//        thay bang dang ky xml
//        model.isLoading.observe(this, Observer {
//            binding.pr.isVisible= it
//        })
    }

    override fun onClick(v: View) {
        val content = binding.edtSearch.text.toString()
        model.searSong(content, this)
    }

    override fun getCount(): Int {
        if (model.songData.value == null) {
            return 0
        }
        return model.songData.value!!.size
    }

    override fun getData(position: Int) = model.songData.value!!.get(position)

    override fun onClickItem(position: Int) {
        model.linkMusic(model.songData.value!![position].linkSong)
        binding.tvName.setText(model.songData.value!![position].songName)
        binding.tvCurrent.setText("--:--")
        binding.tvTotle.setText("--:--")
        asyPlay?.isRunning=false
        asyPlay?.cancel(true)
        asyPlay = null
        binding.seek.progress=0
        //muon lay thong tin bai hat internet thi phai doi den
        //trang thai prepared
    }

    override fun onPrepared() {
        val total = musicOnline.getTotalTime()
        val format = SimpleDateFormat("mm:ss")
        binding.tvTotle.setText(format.format(total))

        asyPlay?.isRunning=false
        asyPlay = MyAsyn()
        asyPlay!!.executeOnExecutor(Executors.newFixedThreadPool(1))
    }

    inner class MyAsyn : AsyncTask<Void, Int?, Void?>() {
        var isRunning = true
        val format = SimpleDateFormat("mm:ss")
        override fun doInBackground(vararg params: Void?): Void? {
            while (isRunning) {
                publishProgress(musicOnline.getCurrentPosition())
                SystemClock.sleep(500)
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Int?) {
            binding.tvCurrent.setText(format.format(values[0]))
            binding.seek.progress = values[0]!! * 100 / musicOnline.total
        }
    }
}