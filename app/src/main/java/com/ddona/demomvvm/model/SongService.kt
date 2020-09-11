package com.ddona.demomvvm.model

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SongService {
    @GET("/api/searchSong")
    fun searchSong(
        @Query(value = "songName") songName:String?
    ):Observable<MutableList<ItemSong>>

    @GET("/api/linkMusic")
    fun linkMusic(
        @Query(value = "linkSong") linkSong:String?
    ):Observable<LinkSong>
}