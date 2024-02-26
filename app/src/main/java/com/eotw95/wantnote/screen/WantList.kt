package com.eotw95.wantnote.screen

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun WantList(
    onClickItem: () -> Unit
) {
    var viewModel: WantListViewModel? = null
    LocalViewModelStoreOwner.current?.let {
        viewModel = viewModel(
            it,
            "WantListViewModel",
            WantListViewModelFactory(LocalContext.current.applicationContext as Application)
        )
    }

    val items = viewModel?.items?.observeAsState()
    viewModel?.fetch()
    println("WantList items=$items")

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        content = {
            val list = items?.value
            if (!list.isNullOrEmpty()) {
                items(list) { item ->
                    Log.d("WantList", "item=$item")
                    GridItem(
                        imageUrl = item.imageUri,
                        onClickItem = onClickItem
                    )
                }
            }
        }
    )
}

@Composable
fun GridItem(
    imageUrl: String,
    onClickItem: () -> Unit
) {
    Log.d("GridItem", "imageUri=${Uri.parse(imageUrl)}")
    Surface(
        modifier = Modifier
            .padding(1.dp)
            .clickable { onClickItem() }
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = Uri.parse(imageUrl)),
            contentDescription = null
        )
    }
}