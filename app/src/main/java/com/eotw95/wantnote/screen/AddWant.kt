package com.eotw95.wantnote.screen

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.eotw95.wantnote.room.WantItem
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWant(
    onClickAdd: () -> Unit,
    onClickSetImage: () -> Unit
) {
    var viewModel: AddWantViewModel? = null
    LocalViewModelStoreOwner.current?.let {
        viewModel = viewModel(
            it,
            "AddWantViewModel",
            AddWantViewModelFactory(LocalContext.current.applicationContext as Application)
        )
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var link by remember { mutableStateOf("") }
        var desc by remember { mutableStateOf("") }
        val imagePath = AddWantViewModel.imagePath.observeAsState()

        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        OutlinedTextField(
            value = link,
            onValueChange = {
                link = it
            },
            placeholder = {
                Text(text = "リンク")
            },
            maxLines = 5
        )
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        Divider(thickness = 0.5.dp)
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        OutlinedButton(onClick = { onClickSetImage() }
        ) {
            Text(text = "画像を追加")
        }
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        Image(
            painter = rememberAsyncImagePainter(model = imagePath.value?.let { File(it) }),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        Divider(thickness = 0.5.dp)
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        OutlinedTextField(
            value = desc,
            onValueChange = {
                desc = it
            },
            placeholder = {
                Text(text = "メモ")
            }
        )
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        Divider(thickness = 0.5.dp)
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        OutlinedButton(
            onClick = {
                onClickAdd()
                viewModel?.add(
                    WantItem(
                        id = 0,
                        link = link,
                        description = desc,
                        imagePath = imagePath.value.toString()
                    )
                )
            }
        ) {
            Text(text = "欲しい物を追加")
        }
    }
}