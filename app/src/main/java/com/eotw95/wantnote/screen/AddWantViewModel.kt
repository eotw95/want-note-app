package com.eotw95.wantnote.screen

import android.app.Application
import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
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
import java.io.IOException

class AddWantViewModel(private val application: Application): ViewModel() {

    companion object {
        private const val TAG = "AddWantViewModel"

        private val mutex = Mutex()
        private var _imageUri = MutableLiveData<Uri>()
        val imageUri: LiveData<Uri> = _imageUri
    }

    private val db = WantDatabase.getInstanse(application.applicationContext)
    private val wantRepository = WantRepository(db)

    fun setImage(image: Uri) {
        Log.d("AddWantViewModel", "setImage=${image}")
        try {
            _imageUri.postValue(image)
        } catch (e: NullPointerException) {
            Log.e(TAG, "image is null")
        }
        Log.d("AddWantViewModel", "imagerUri=${imageUri.value}")
        Log.d("AddWantViewModel", "_imagerUri=${_imageUri.value}")
    }

    fun add(
        link: String,
        desc: String,
        image: Uri
    ) {
        viewModelScope.launch {
            mutex.withLock {
                withContext(Dispatchers.IO) {
                    uriToBitmap(application.contentResolver, image)?.let {
                        wantRepository.insert(
                            WantItem(
                                link = link,
                                description = desc,
                                image = it
                            )
                        )
                    }
                }
            }
        }
    }

    private fun uriToBitmap(contentResolver: ContentResolver, uri: Uri): Bitmap? {
        return try {
            val inputStream = contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.stackTraceToString()
            null
        }
    }

}