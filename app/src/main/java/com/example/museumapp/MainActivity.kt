package com.example.museumapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.museumapp.util.ShakeDetector
import com.example.museumapp.navigation.Navigation
import com.example.museumapp.ui.theme.MuseumAppTheme
import com.example.museumapp.viewModel.MuseumViewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.museumapp.composable.CollectionDetailView
import com.example.museumapp.composable.HomePage
import com.example.museumapp.navigation.NavigationItem
import com.example.museumapp.room.FavouriteListView
import com.example.museumapp.viewModel.FavouriteItemViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

//    private val viewModel by viewModels<MuseumViewModel>()
//    private lateinit var museumDatabase: MuseumDatabase
//    private lateinit var museumViewModel: MuseumViewModel
    private val museumViewModel: MuseumViewModel by viewModels()
    private val favouriteItemViewModel: FavouriteItemViewModel by viewModels()
    private var mSensorManager: SensorManager? = null
    private var mShakeDetector: ShakeDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mShakeDetector = ShakeDetector()

//        museumViewModel = ViewModelProvider(this).get(MuseumViewModel::class.java)
//        favouriteItemViewModel = ViewModelProvider(this).get(FavouriteItemViewModel::class.java)



        setContent {
            AppContent(museumViewModel, mShakeDetector)

        }
    }

    @Composable
    fun AppContent(viewModel: MuseumViewModel, shakeDetector: ShakeDetector?) {
        val navController = rememberNavController()

        shakeDetector?.setOnShakeListener {
            navController.navigate(NavigationItem.Favourite.route)
        }

        MuseumAppTheme {
            Navigation(viewModel)
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


