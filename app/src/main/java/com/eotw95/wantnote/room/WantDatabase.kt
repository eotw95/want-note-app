package com.eotw95.wantnote.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WantItem::class], version = 1)
abstract class WantDatabase(context: Context): RoomDatabase() {
    companion object {
        private var instanse: WantDatabase? = null

        fun getInstanse(context: Context): WantDatabase {
            return instanse ?: synchronized(this) {
                val tmpInstanse = Room.databaseBuilder(
                    context,
                    WantDatabase::class.java,
                    "WantDatabase"
                ).build()
                instanse = tmpInstanse

                return instanse as WantDatabase
            }
        }
    }
    abstract fun wantDao(): WantDao
}