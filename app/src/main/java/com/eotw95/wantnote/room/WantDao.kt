package com.eotw95.wantnote.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WantDao {
    @Query("SELECT * FROM wantItems")
    fun getAll(): List<WantItem>

    @Insert
    fun insert(item: WantItem)

    @Update
    fun update(item: WantItem)

    @Delete
    fun delete(item: WantItem)
}