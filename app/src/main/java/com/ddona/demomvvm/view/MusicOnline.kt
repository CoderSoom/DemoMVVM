package com.ddona.demomvvm.view

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import java.net.URI

class MusicOnline : MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener,
    MediaPlayer.OnBufferingUpdateListener {
    var player: MediaPlayer? = null
    var inter: IMusicOnline? = null
    var total = 0

    fun setDataSource(context: Context, link: String) {
        player = MediaPlayer()
        player!!.setOnErrorListener(this)
        player!!.setOnBufferingUpdateListener(this)
        player!!.setDataSource(context, Uri.parse(link))

        //dang ky prepared
        player!!.setOnPreparedListener(this)
        player!!.prepareAsync()
    }

    override fun onError(mp: MediaPlayer, what: Int, extra: Int): Boolean {
        Log.d("MusicOnline", "onError.......")
        return true
    }

    override fun onPrepared(mp: MediaPlayer) {
        //
        total = mp.duration
        inter?.onPrepared()

        mp.start()
    }

    fun getTotalTime(): Int {
        return player!!.duration
    }

    fun getCurrentPosition(): Int {
        return player!!.currentPosition
    }

    fun play() {
        if (player == null) {
            return
        }
        if (player!!.isPlaying) {
            return
        }
        player!!.start()
    }

    fun stop() {
        if (player == null) {
            return
        }
        player!!.stop()

    }

    fun pause() {
        if (player == null) {
            return
        }
        player!!.pause()
    }

    fun release() {
        player?.release()
        player = null
    }

    override fun onBufferingUpdate(mp: MediaPlayer, percent: Int) {
        Log.d("MusicOnline", "percent......." + percent)
    }

    interface IMusicOnline {
        fun onPrepared()
    }

}