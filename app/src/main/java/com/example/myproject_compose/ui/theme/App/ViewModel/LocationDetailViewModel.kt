package com.example.myproject_compose.ui.theme.App.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myproject_compose.ui.theme.App.Response.LocationResponse
import com.example.myproject_compose.ui.theme.App.repasitory.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationDetailViewModel(private val repository: Repository) : ViewModel() {

    private val _location = MutableStateFlow<LocationResponse.Location?>(null)
    val location: StateFlow<LocationResponse.Location?> get() = _location

    fun fetchLocationById(id: Int) {
        viewModelScope.launch {
            val response = repository.getLocationById(id)
            if (response.isSuccessful) {
                _location.value = response.body()
            }
        }
    }
}