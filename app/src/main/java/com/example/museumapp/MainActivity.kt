package com.example.museumapp

import android.graphics.Color
import android.graphics.ColorFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.museumapp.data.remote.MuseumService
import com.example.museumapp.data.remote.dto.MuseumItem
import com.example.museumapp.data.remote.dto.MuseumRecord
import com.example.museumapp.data.remote.dto.MuseumResponse
import com.example.museumapp.data.remote.dto.PostResponse
import com.example.museumapp.ui.theme.MuseumAppTheme
import io.reactivex.plugins.RxJavaPlugins.onError

class MainActivity : ComponentActivity() {

    //use dependency injection in view model
    private val service = MuseumService.create()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //val museumResponse = MuseumService.
            val museum = produceState<List<MuseumItem>>(
                //val museum = produceState<List<MuseumResponse>>(
                //val postsData = produceState<List<PostResponse>>(
                //val museumData = produceState<List<MuseumRecord>>(
                initialValue = emptyList(),
                producer = {
                    try {
                        value = service.getMuseum()

                    } catch (e: Exception) {
                        // Handle errors here, e.g., show an error message
                        // and set an appropriate value for museumData
                    }

                }
            )
            
            MuseumAppTheme {
                Surface {
                    LazyColumn {
                        items(museum.value) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                // Display the image using the Image composable
                                val painter =
                                    // You can configure image loading options here if needed
                                    rememberAsyncImagePainter(
                                        ImageRequest.Builder(LocalContext.current)
                                            .data(data = it.images).apply(block = fun ImageRequest.Builder.() {
                                                // You can configure image loading options here if needed
                                            }).build()
                                    )
                                Image(
                                    painter = painter,
                                    contentDescription = null, // Provide a meaningful description
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp), // Adjust the height as needed
                                    contentScale = ContentScale.Crop, // Adjust the content scale as needed
                                    alignment = Alignment.TopStart,
                                   // colorFilter = ColorFilter.tint(Color.White) // Optional: Apply a tint color
                                )
                                Text(text = it.title, fontSize = 20.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = "${it.year}", fontSize = 14.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                            }
                        }
                    }
                }
                /*Surface {
                    LazyColumn {
                        items(museumData.value) {
                            Column(modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                            ) {
                                Text(text = it.title, fontSize = 20.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = it.year, fontSize = 16.sp)
                            }
                        }
                    }
                }*/
            }
        }
    }
}
