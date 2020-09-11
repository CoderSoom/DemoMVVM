package com.ddona.demomvvm.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ddona.demomvvm.model.ItemSong
import io.reactivex.Single

@Dao
open interface ItemSongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    open fun insetAll(articles: MutableList<ItemSong>)

    @Query("SELECT * FROM item_song WHERE id= :id")
    open fun getArticleById(id: Int): Single<ItemSong>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    open fun insertItemSong(itemsSong: ItemSong)

    @Query("DELETE FROM item_song WHERE keySearch = :keySearch")
    open fun delete(keySearch: String)

    @Query("SELECT * FROM item_song WHERE keySearch = :keySearch")
    open fun findByKeySearch(keySearch: String): MutableList<ItemSong>
}