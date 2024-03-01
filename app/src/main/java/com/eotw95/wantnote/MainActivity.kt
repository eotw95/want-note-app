package com.eotw95.wantnote

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.eotw95.wantnote.screen.AddWantViewModel
import com.eotw95.wantnote.screen.AddWantViewModelFactory
import com.eotw95.wantnote.ui.theme.WantNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: AddWantViewModel by viewModels {
            AddWantViewModelFactory(application)
        }
        val launcher = registerForActivityResult(ActivityResultContracts.OpenDocument()) { tmpUri ->
            tmpUri?.let { viewModel.setImage(it) }
        }

        setContent {
            WantNoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WantNoteApp(
                        startImageGallery = { launcher.launch(arrayOf("image/*")) }
                    )
                }
            }
        }
    }
}