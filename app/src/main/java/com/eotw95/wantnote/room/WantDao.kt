package com.eotw95.wantnote.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WantDao {
    @Query("SELECT * FROM wantItems")
    suspend fun getAll(): List<WantItem>

    @Insert
    suspend fun insert(item: WantItem)

    @Update
    suspend fun update(item: WantItem)

    @Delete
    suspend fun delete(item: WantItem)
}