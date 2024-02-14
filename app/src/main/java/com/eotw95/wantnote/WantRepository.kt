package com.eotw95.wantnote

import com.eotw95.wantnote.room.WantDatabase
import com.eotw95.wantnote.room.WantItem

class WantRepository(private val db: WantDatabase) {

    suspend fun getAll(): List<WantItem> {
        return db.wantDao().getAll()
    }

    suspend fun insert(item: WantItem) {
        db.wantDao().insert(item)
    }

    suspend fun update(item: WantItem) {
        db.wantDao().update(item)
    }

    suspend fun delete(item: WantItem) {
        db.wantDao().delete(item)
    }
}