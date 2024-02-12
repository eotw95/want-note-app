package com.eotw95.wantnote.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eotw95.wantnote.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewWant(
    onClickBack: () -> Unit,
    onClickEdit: () -> Unit
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.clickable { onClickBack() }
                )
                Row {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null,
                        modifier = Modifier.clickable { onClickEdit() }
                    )
                    Spacer(modifier = Modifier.padding(15.dp))
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        modifier = Modifier.clickable { /* Todo: delete item */ }
                    )
                }
            }
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sample_image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(vertical = 30.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Filled.Info, contentDescription = null)
                    Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                    Text(text = "https://hosiimono.com")
                }
                Spacer(modifier = Modifier.padding(vertical = 30.dp))
                Text(text = "テストテストテストテストテストテストテストテストテストテストテストテストテストテストテスト" +
                        "テストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテスト" +
                        "テストテストテストテストテストテストテストテストテストテストテストテストテスト")
            }
        }
    }
}