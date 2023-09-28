package com.example.museumapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museumapp.data.remote.MuseumService
import com.example.museumapp.data.remote.dto.MuseumResponse
import kotlinx.coroutines.launch

class MuseumViewModel : ViewModel() {

    private val service = MuseumService.create()

    val museum: State<List<MuseumResponse>> = mutableStateOf(emptyList())

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val data = service.getRecords()
                //museum.value = data
            } catch (e: Exception) {
                // Handle network errors or exceptions
            }
        }
    }
}
