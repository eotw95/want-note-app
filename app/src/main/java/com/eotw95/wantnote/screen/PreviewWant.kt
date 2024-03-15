package com.eotw95.wantnote.screen

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.eotw95.wantnote.KEY_EMPTY
import java.io.File
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewWant(
    id: Int,
    link: String,
    description: String,
    imagePath: String,
    onClickBack: () -> Unit,
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit,
    onClickUri: (String) -> Unit
) {
    var previewVM: PreviewWantViewModel? = null
    LocalViewModelStoreOwner.current?.let {
        previewVM = viewModel(
            it,
            "PreviewWantViewModel",
            PreviewWantViewModelFactory(LocalContext.current.applicationContext as Application)
        )
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clickable { onClickBack() }
                )
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        Log.d("PreviewWantList", "id=$id")
                        previewVM?.delete(id)
                        onClickDelete()
                    }
                )
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().background(Color.Black),
                horizontalAlignment = Alignment.Start
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = File(imagePath)),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.FillWidth
                )
                Spacer(modifier = Modifier.padding(vertical = 15.dp))

                var isLink = false
                val linkText = if (link.startsWith("http")) {
                    isLink = true
                    if (link.length > 30) link.substring(0, 40) + "..."
                    else link
                } else {
                    "商品リンク無し"
                }
                val encoderUrl = URLEncoder.encode(link, StandardCharsets.UTF_8.toString())

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .clickable {
                        if (isLink) onClickUri(encoderUrl)
                    }
                ) {
                    Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                    Text(text = linkText, color = Color.White)
                }
                Spacer(modifier = Modifier.padding(vertical = 15.dp))
                Divider(color = Color.White, thickness = 0.2.dp, modifier = Modifier.padding(horizontal = 15.dp))
                Spacer(modifier = Modifier.padding(vertical = 15.dp))
                Text(
                    text = if (description == KEY_EMPTY) "" else description,
                    modifier = Modifier.padding(horizontal = 15.dp),
                    color = Color.White
                )
            }
        }
    }
}