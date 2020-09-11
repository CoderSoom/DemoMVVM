package com.ddona.demomvvm.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddona.demomvvm.model.ItemSong
import com.ddona.demomvvm.model.LinkSong
import com.ddona.demomvvm.model.RetrofitUtils
import com.ddona.demomvvm.model.SongService
import com.ddona.demomvvm.model.database.AppDatabase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SongViewModel : ViewModel {

    private val songService: SongService
    //ObservableField la mot dang liveData

    //khi MutableLiveData nhan du lieu thi MutableLiveData
    // se thong bao den view dang ky
    val songData: MutableLiveData<MutableList<ItemSong>>
    val linkSong: MutableLiveData<LinkSong>

    //muon xml nhan duoc thi phai dung  Observable......
    val isLoading: ObservableBoolean

    constructor() {
        isLoading = ObservableBoolean()
        isLoading.set(false)


        songData = MutableLiveData<MutableList<ItemSong>>()
        linkSong = MutableLiveData<LinkSong>()

        songService = RetrofitUtils.createRetrofit(
//            "https://songserver.herokuapp.com",
            " http://b82101864212.ngrok.io",
            SongService::class.java
        )
    }

    @SuppressLint("CheckResult")
    fun searSong(content: String, context:Context) {
        //tao khong gian
        isLoading.set(true)
        songService.searchSong(content)
            //tuong tac voi internet no nam o thread nao
            .subscribeOn(Schedulers.newThread())
            //sau khi tuong tac voi internet thi ket qua se ki ve
            .observeOn(AndroidSchedulers.mainThread())
            //bat dau thuc thi khong gian
            .subscribe(
                {
                    //main thread
                    //sinh co che do thong bao ve view: LiveData
                    Log.d("SongViewModel", "observe finish...................")
                    isLoading.set(false)
                    songData.value = it

                    for (itemSong in it) {
                        itemSong.keySearch=content
                    }
                    Observable.just(it)
                        .observeOn(Schedulers.newThread())
                        .subscribe {
                            AppDatabase.getInstance(context)
                                .itemSongDao()
                                .insetAll(it)
                        }


                },
                {
                    isLoading.set(false)
                    val items = AppDatabase.getInstance(context)
                        .itemSongDao()
                        .findByKeySearch(keySearch = content)

                    songData.value = items
                    it.printStackTrace()
                }
            )
    }

    fun linkMusic(link:String){
        isLoading.set(true)
        songService.linkMusic(link)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    isLoading.set(false)
                    linkSong.value=it
                },
                {
                    isLoading.set(false)
                }
            )
    }

}