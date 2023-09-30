package com.example.museumapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.museumapp.composable.CollectionList
import com.example.museumapp.ui.theme.MuseumAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MuseumViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MuseumAppTheme {
                Navigation(viewModel)
            }
        }
    }
}
