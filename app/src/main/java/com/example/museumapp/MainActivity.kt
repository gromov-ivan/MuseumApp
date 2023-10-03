package com.example.museumapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.example.museumapp.util.ShakeDetector
import com.example.museumapp.navigation.Navigation
import com.example.museumapp.ui.theme.MuseumAppTheme
import com.example.museumapp.viewModel.MuseumViewModel
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MuseumViewModel>()
    private var mSensorManager: SensorManager? = null
    private var mShakeDetector: ShakeDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mShakeDetector = ShakeDetector()

        setContent {
            AppContent(viewModel, mShakeDetector)
        }
    }

    @Composable
    fun AppContent(viewModel: MuseumViewModel, shakeDetector: ShakeDetector?) {
        val navController = rememberNavController()

        shakeDetector?.setOnShakeListener {
            navController.navigate("collectionsCard")
        }

        MuseumAppTheme {
            Navigation(viewModel, navController)
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

