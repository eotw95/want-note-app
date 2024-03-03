package com.eotw95.wantnote.screen

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import java.io.File

@Composable
fun WantList(
    onClickItem: (String, String, String, String) -> Unit
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

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        content = {
            val list = items?.value
            if (!list.isNullOrEmpty()) {
                items(list) { item ->
                    GridItem(
                        id = item.id,
                        link = item.link,
                        desc = item.description,
                        imagePath = item.imagePath,
                        onClickItem = onClickItem
                    )
                }
            }
        }
    )
}

@Composable
fun GridItem(
    id: Int,
    link: String,
    desc: String,
    imagePath: String,
    onClickItem: (String, String, String, String) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(1.dp)
            .clickable {
                onClickItem(
                    id.toString(),
                    link,
                    desc,
                    imagePath
                )
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = File(imagePath)),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(150.dp)
        )
    }
}