package com.example.museumapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.example.museumapp.ui.theme.MuseumAppTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MuseumAppTheme {
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
            }
        }
    }
}