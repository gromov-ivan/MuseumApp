package com.example.museumapp

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.museumapp.util.ShakeDetector
import com.example.museumapp.ui.theme.MuseumAppTheme
import com.example.museumapp.viewModel.MuseumViewModel
import com.example.museumapp.composable.FeedbackBottomSheet
import com.example.museumapp.navigation.Navigation
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MuseumViewModel>()
    private var mSensorManager: SensorManager? = null
    private var mShakeDetector: ShakeDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mShakeDetector = ShakeDetector()

        setContent {
            AppContent(viewModel)

            val systemUiController = rememberSystemUiController()
            val useDarkIcons = !isSystemInDarkTheme()

            SideEffect {
                systemUiController.setStatusBarColor(
                    color = Color(0xFFFFFBFE),
                    darkIcons = useDarkIcons,
                )
            }
        }
    }

    @Composable
    fun AppContent(viewModel: MuseumViewModel) {
        rememberNavController()

        MuseumAppTheme {
            Navigation(viewModel)

            FeedbackBottomSheet(
                shakeDetector = mShakeDetector,
                onSendFeedback = { feedback ->
                    val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "ivan@metropolia.fi", null)).apply {
                        putExtra(Intent.EXTRA_SUBJECT, "Museum App Feedback")
                        putExtra(Intent.EXTRA_TEXT, feedback)
                    }
                    startActivity(emailIntent)
                }
            )
        }
    }

    override fun onResume() {
        super.onResume()
        mSensorManager?.registerListener(mShakeDetector, mSensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI)
    }

    override fun onPause() {
        mSensorManager?.unregisterListener(mShakeDetector)
        super.onPause()
    }
}
