package com.eotw95.wantnote.screen

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eotw95.wantnote.WantRepository
import com.eotw95.wantnote.room.WantDatabase
import com.eotw95.wantnote.room.WantItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class WantListViewModel(private val application: Application): ViewModel() {

    companion object {
        private val mutex = Mutex()
    }

    private val db = WantDatabase.getInstanse(application.applicationContext)
    private val wantRepository = WantRepository(db)

    private var _items = MutableLiveData<List<WantItem>>()
    val items: LiveData<List<WantItem>>
        get() = _items

    init {
        fetch()
    }

    fun fetch() {
        viewModelScope.launch {
            mutex.withLock {
                withContext(Dispatchers.IO) {
                    _items.postValue(wantRepository.getAll())
                }
            }
        }
    }
}