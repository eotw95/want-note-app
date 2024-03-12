package com.eotw95.wantnote

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.eotw95.wantnote.screen.AddWantViewModel
import com.eotw95.wantnote.screen.AddWantViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WantNoteApp(
    startImageGallery: () -> Unit
) {
    val navController = rememberNavController()
    var addVM: AddWantViewModel? = null
    LocalViewModelStoreOwner.current?.let {
        addVM = viewModel(
            it,
            "AddWantViewModel",
            AddWantViewModelFactory(LocalContext.current.applicationContext as Application)
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    addVM?.setImage(null)
                    navController.navigate(Screens.Add.route)
                }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    ) {
        Spacer(modifier = Modifier.padding(it))
        AppNavigation(
            navController,
            startImageGallery = startImageGallery
        )
    }
}