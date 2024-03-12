package com.eotw95.wantnote.screen

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
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
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddWantViewModel(private val application: Application): ViewModel() {

    companion object {
        private const val TAG = "AddWantViewModel"

        private val mutex = Mutex()
        private var _imagePath = MutableLiveData<String>()
        val imagePath: LiveData<String> = _imagePath
    }

    private val db = WantDatabase.getInstanse(application.applicationContext)
    private val wantRepository = WantRepository(db)

    fun setImage(image: Uri?) {
        uriToBitmap(application.applicationContext.contentResolver, image)?.let { bitmap ->
            saveImageInternalStorage(bitmap)?.let {path ->
                _imagePath.postValue(path)
            }
        } ?: _imagePath.postValue(null)
    }

    fun add(item: WantItem) {
        viewModelScope.launch {
            mutex.withLock {
                withContext(Dispatchers.IO) {
                    wantRepository.insert(item)
                }
            }
        }
    }

    private fun uriToBitmap(contentResolver: ContentResolver, uri: Uri?): Bitmap? {
        return if (uri != null) {
            try {
                val inputStream = contentResolver.openInputStream(uri)
                BitmapFactory.decodeStream(inputStream)
            } catch (e: IOException) {
                e.stackTraceToString()
                null
            }
        } else {
            null
        }
    }

    private fun saveImageInternalStorage(image: Bitmap): String? {
        var path: String? = null

        val dir = ContextWrapper(application.applicationContext)
            .getDir("image", Context.MODE_PRIVATE)
        val file = File(dir, createUniqueName())

        try {
            val outputStream = FileOutputStream(file)
            image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            path = file.absolutePath
        } catch (e: IOException) {
            e.stackTraceToString()
        }

        return path
    }

    private fun createUniqueName(): String {
        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.JAPANESE)
        val now = Date()
        val strDate = dateFormat.format(now)
        return "image_$strDate.jpg"
    }

}