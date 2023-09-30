package com.example.museumapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.museumapp.composable.CollectionList
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.example.museumapp.ui.theme.MuseumAppTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MuseumViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val museumData by viewModel.museumData.observeAsState(emptyList())

            val navController = rememberNavController()
            MuseumAppTheme {
                CollectionList(museumData) // Pass the initial value from the ViewModel

                // Observe changes in museumData
                LaunchedEffect(viewModel) {
                    viewModel.fetchAteneumGraphics()
                }
                Surface {
                    NavHost(navController, startDestination = "Home") {
                        composable("Home") {
                            Column {
                                Text(text = "Home page")
                                Button(onClick = {
                                    navController.navigate("QRCodeView")
                                }) {
                                    Text(text = "Scan the QR code")
                                }
                                Button(onClick = {
                                    navController.navigate("FavouriteAnimatedView")
                                }) {
                                    Text(text = "FavouriteAnimatedView")
                                }
                            }

                        }
                        composable("QRCodeView") {
                            QRCodeView()
                        }
                        composable("FavouriteAnimatedView") {
                            FavouriteAnimatedView()
                        }
                    }
                    // Fetch museum data when the activity is created
                    viewModel.fetchAteneumGraphics()
                }
            }
        }
    }
}