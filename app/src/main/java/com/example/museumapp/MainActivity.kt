package com.example.museumapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.museumapp.data.remote.MuseumService
import com.example.museumapp.data.remote.dto.MuseumItem
import com.example.museumapp.ui.theme.MuseumAppTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {

    //use dependency injection in view model
    private val service = MuseumService.create()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val museum = produceState<List<MuseumItem>>(
                initialValue = emptyList(),
                producer = {
                    try {
                        value = service.getMuseumArt()

                    } catch (e: Exception) {
                        // Handle errors here, e.g., show an error message
                        // and set an appropriate value for museumData
                    }

                }
            )

            val navController = rememberNavController()
            MuseumAppTheme {
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
                            }

                        }
                        composable("QRCodeView") {
                            QRCodeView()
                        }
                    }
                    LazyColumn {
                        items(museum.value) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                val painter =
                                    rememberAsyncImagePainter(
                                        ImageRequest.Builder(LocalContext.current)
                                            .data(data = it.images).apply(block = fun ImageRequest.Builder.() {
                                                // You can configure image loading options here if needed
                                            }).build()
                                    )
                                Image(
                                    painter = painter,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp), // Adjust the height as needed
                                    contentScale = ContentScale.Crop, // Adjust the content scale as needed
                                    alignment = Alignment.TopStart,
                                )
                                Text(text = it.title, fontSize = 20.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = "${it.year}", fontSize = 14.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}
