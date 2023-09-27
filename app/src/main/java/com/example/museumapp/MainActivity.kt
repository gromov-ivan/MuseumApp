package com.example.museumapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.museumapp.data.remote.MuseumService
import com.example.museumapp.data.remote.dto.MuseumResponse
import com.example.museumapp.ui.theme.MuseumAppTheme

class MainActivity : ComponentActivity() {

    //use dependency injection in view model
    private val service = MuseumService.create()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val museumData = produceState<List<MuseumResponse>>(
                initialValue = emptyList(),
                producer = {
                    try {
                        value = service.getRecords()
                    } catch (e: Exception) {
                        // Handle errors here, e.g., show an error message
                        // and set an appropriate value for museumData
                    }

                }
            )
            
            MuseumAppTheme {
                Surface {
                    //LazyColumn {
                        //items(museumData.value) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(text = "loading ")
                    }
                }
            }
        }
    }
}
