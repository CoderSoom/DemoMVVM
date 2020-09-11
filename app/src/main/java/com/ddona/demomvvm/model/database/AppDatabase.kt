package com.ddona.demomvvm.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ddona.demomvvm.model.ItemSong

@Database(entities = arrayOf(ItemSong::class),version = 1)
open abstract class AppDatabase : RoomDatabase() {
    open abstract fun itemSongDao(): ItemSongDao

    companion object {
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "app.db")
                .allowMainThreadQueries()
                .build()

        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
//            return INSTANCE ?: synchronized(this) {
//                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
//            }
            if ( INSTANCE == null){
                INSTANCE = buildDatabase(context)
            }
            return INSTANCE!!
        }

    }


}