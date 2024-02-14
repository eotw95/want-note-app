package com.eotw95.wantnote.screen

import android.app.Application
import androidx.lifecycle.ViewModel
import com.eotw95.wantnote.WantRepository
import com.eotw95.wantnote.room.WantDatabase

class PreviewWantViewModel(private val application: Application): ViewModel() {

    private val db = WantDatabase.getInstanse(application.applicationContext)
    private val wantRepository = WantRepository(db)

    // Todo:
}