package com.eotw95.wantnote.screen

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eotw95.wantnote.WantRepository
import com.eotw95.wantnote.room.WantDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class PreviewWantViewModel(private val application: Application): ViewModel() {

    companion object {
        private val mutex = Mutex()
    }

    private val db = WantDatabase.getInstanse(application.applicationContext)
    private val wantRepository = WantRepository(db)

    fun delete(id: Int) {
        viewModelScope.launch {
            mutex.withLock {
                withContext(Dispatchers.IO) {
                    val item = wantRepository.getItemById(id)
                    wantRepository.delete(item)
                    WantListViewModel(application).fetch()
                }
            }
        }
    }
}