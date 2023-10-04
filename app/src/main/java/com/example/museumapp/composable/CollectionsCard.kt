package com.example.museumapp.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.museumapp.viewModel.MuseumViewModel

@Composable
fun CollectionsCard(
    navController: NavController,
    viewModel: MuseumViewModel,
    selectedCard: (String) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Choose the museum you are interested in...",
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 64.dp),
                textAlign = TextAlign.Left,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 0.dp
                ),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier
                    .padding(8.dp)
                    .size(width = 240.dp, height = 60.dp)
                    .padding(4.dp)
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
                    defaultElevation = 0.dp
                ),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier
                    .padding(8.dp)
                    .size(width = 240.dp, height = 60.dp)
                    .padding(4.dp)
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
                    defaultElevation = 0.dp
                ),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier
                    .padding(8.dp)
                    .size(width = 240.dp, height = 60.dp)
                    .padding(4.dp)
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

            /*
            Text(
                text = "Tip: shake your phone whenever you want to select a different museum.",
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Left,
                fontSize = 18.sp
            )
            */
        }
    }
}