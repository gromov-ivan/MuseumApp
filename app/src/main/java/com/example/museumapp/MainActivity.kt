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

            val museumData by viewModel.museumData.observeAsState(emptyList())
            MuseumAppTheme {
                CollectionList(museumData) // Pass the initial value from the ViewModel

                // Observe changes in museumData
                LaunchedEffect(viewModel) {
                    viewModel.fetchAteneumGraphics()
                }
            }
        }
        // Fetch museum data when the activity is created
        viewModel.fetchAteneumGraphics()
    }
}
