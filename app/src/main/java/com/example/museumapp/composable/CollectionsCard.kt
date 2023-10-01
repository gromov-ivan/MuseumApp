package com.example.museumapp.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.museumapp.MuseumViewModel

@Composable
fun CollectionsCard(
    navController: NavController,
    viewModel: MuseumViewModel,
    selectedCard: (String) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Choose the museum you are interested about.",
                modifier = Modifier
                    .padding(5.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp)


            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier
                    .size(width = 240.dp, height = 100.dp)
                    .clickable {
                        selectedCard("Tuusula Museum")
                        // Call the function to load Tuusula Museum data
                        viewModel.fetchTuusulaDrawings()
                        // Navigate to the "collectionList" screen
                        navController.navigate("collectionList")
                    },

                ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Tuusula Museum",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )
                }
            }

            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier
                    .size(width = 240.dp, height = 100.dp)
                    .clickable {
                        selectedCard("Ateneum Museum")
                        // Call the function to load Ateneum Museum data
                        viewModel.fetchAteneumGraphics()
                        navController.navigate("collectionList")
                    },

                ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Ateneum Museum",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )
                }
            }

            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier
                    .size(width = 240.dp, height = 100.dp)
                    .clickable {
                        selectedCard("Photography Museum")
                        // Call the function to load Photograph Museum data
                        viewModel.fetchCitiesPhotograhs()
                        navController.navigate("collectionList")
                    },

                )  {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Photography Museum",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}