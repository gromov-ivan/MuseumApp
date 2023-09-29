package com.example.museumapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museumapp.data.remote.MuseumService
import com.example.museumapp.data.remote.dto.MuseumItem
import kotlinx.coroutines.launch

class MuseumViewModel : ViewModel() {

    private val service = MuseumService.create()

    // LiveData to hold the museum data
    private val _museumData = MutableLiveData<List<MuseumItem>>()
    val museumData: LiveData<List<MuseumItem>> = _museumData

    // Function to fetch Tuusula drawings data
    fun fetchTuusulaDrawings() {
        viewModelScope.launch {
            try {
                val drawingsData = service.getTuusulaDrawings()
                _museumData.value = drawingsData
            } catch (e: Exception) {
                Log.e("DBG", "Error fetching data: ${e.message}")
            }
        }
    }

    // Function to fetch Tuusula pictures data
    fun fetchTuusulaPictures() {
        viewModelScope.launch {
            try {
                val picturesData = service.getTuusulaPitures()
                _museumData.value = picturesData
            } catch (e: Exception) {
                Log.e("DBG", "Error fetching data: ${e.message}")
            }
        }
    }

    // Function to fetch agriculture photographs data
    fun fetchAgriculturePhotographs() {
        viewModelScope.launch {
            try {
                val agricultureData = service.getAgriculturePhotography()
                _museumData.value = agricultureData
            } catch (e: Exception) {
                Log.e("DBG", "Error fetching data: ${e.message}")
            }
        }
    }

    // Function to fetch cities photographs data
    fun fetchCitiesPhotograhs() {
        viewModelScope.launch {
            try {
                val citiesData = service.getCitiesPhotography()
                _museumData.value = citiesData
            } catch (e: Exception) {
                Log.e("DBG", "Error fetching data: ${e.message}")
            }
        }
    }

    // Function to fetch Ateneum graphics data
    fun fetchAteneumGraphics() {
        viewModelScope.launch {
            try {
                val graphicsData = service.getAteneumGraphics()
                _museumData.value = graphicsData
            } catch (e: Exception) {
                Log.e("DBG", "Error fetching data: ${e.message}")
            }
        }
    }

    // Function to fetch Ateneum sculptures data
    fun fetchAteneumSculptures() {
        viewModelScope.launch {
            try {
                val sculptureData = service.getAteneumSculpture()
                _museumData.value = sculptureData
            } catch (e: Exception) {
                Log.e("DBG", "Error fetching data: ${e.message}")
            }
        }
    }

}
