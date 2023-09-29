package com.example.museumapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museumapp.data.remote.MuseumService
import com.example.museumapp.data.remote.dto.MuseumItem
import com.example.museumapp.data.remote.dto.MuseumResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MuseumViewModel : ViewModel() {

    private val service = MuseumService.create()

    // LiveData to hold the museum data
    private val _museumData = MutableLiveData<List<MuseumItem>>()
    val museumData: LiveData<List<MuseumItem>> = _museumData

    // Function to fetch museum data
    fun fetchMuseumData() {
        viewModelScope.launch {
            try {
                val data = service.getMuseumArt()
                _museumData.value = data
            } catch (e: Exception) {
                // Handle errors here, e.g., show an error message
            }
        }
    }

}
