package com.eotw95.wantnote.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWant(
    onClickAdd: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var link by remember { mutableStateOf("") }
        var desc by remember { mutableStateOf("") }

        Spacer(modifier = Modifier.padding(vertical = 30.dp))
        OutlinedTextField(
            value = link,
            onValueChange = {
                link = it
            },
            placeholder = {
                Text(text = "リンク")
            }
        )
        Spacer(modifier = Modifier.padding(vertical = 30.dp))
        Divider(thickness = 0.5.dp)
        Spacer(modifier = Modifier.padding(vertical = 30.dp))
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(text = "画像を追加")
        }
        Spacer(modifier = Modifier.padding(vertical = 30.dp))
        Divider(thickness = 0.5.dp)
        Spacer(modifier = Modifier.padding(vertical = 30.dp))
        OutlinedTextField(
            value = desc,
            onValueChange = {
                desc = it
            },
            placeholder = {
                Text(text = "メモ")
            }
        )
        Spacer(modifier = Modifier.padding(vertical = 30.dp))
        Divider(thickness = 0.5.dp)
        Spacer(modifier = Modifier.padding(vertical = 30.dp))
        OutlinedButton(
            onClick = onClickAdd
        ) {
            Text(text = "欲しい物を追加")
        }
    }
}