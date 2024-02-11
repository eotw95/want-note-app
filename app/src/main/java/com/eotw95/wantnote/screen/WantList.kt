package com.eotw95.wantnote.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eotw95.wantnote.R

@Composable
fun WantList() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        content = {
            items(30) {
                GridItem()
            }
        }
    )
}

@Composable
fun GridItem() {
    Surface(
        modifier = Modifier.padding(1.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.sample_image),
            contentDescription = null
        )
    }
}