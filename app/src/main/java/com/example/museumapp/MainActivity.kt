package com.example.museumapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.museumapp.composable.CollectionList

import com.example.museumapp.navigation.Navigation

import com.example.museumapp.navigation.NavigationItem
import com.example.museumapp.ui.theme.MuseumAppTheme
import com.example.museumapp.util.ShakeDetector
import com.example.museumapp.viewModel.FavouriteViewModel
import com.example.museumapp.viewModel.MuseumViewModel

class MainActivity : ComponentActivity() {

    private val museumViewModel by viewModels<MuseumViewModel>()
    private val favouriteViewModel by viewModels<FavouriteViewModel>()
    private var mSensorManager: SensorManager? = null
    private var mShakeDetector: ShakeDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mShakeDetector = ShakeDetector()

        setContent {
            AppContent(museumViewModel, mShakeDetector, favouriteViewModel )
        }
    }

    @Composable
    fun AppContent(
        viewModel: MuseumViewModel,
        shakeDetector: ShakeDetector?,
        favouriteViewModel: FavouriteViewModel
    ) {
        val navController = rememberNavController()

        shakeDetector?.setOnShakeListener {
            navController.navigate(NavigationItem.Favourite.route)
        }

        MuseumAppTheme {
            Navigation(viewModel,favouriteViewModel)
            CollectionList(
            museumItems = emptyList(),
            viewModel = viewModel,
            selectedCard = toString(),
            navController= navController,
            favouriteViewModel = favouriteViewModel,
            )
        }
    }

    override fun onResume() {
        super.onResume()
        mSensorManager?.registerListener(
            mShakeDetector,
            mSensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_UI
        )
    }

    override fun onPause() {
        mSensorManager?.unregisterListener(mShakeDetector)
        super.onPause()
    }
}
