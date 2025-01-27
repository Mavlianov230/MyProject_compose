package com.example.myproject_compose.ui.theme.repasitory

import android.util.Log
import com.example.homework_jc.data.model.EpisodeResponse
import com.example.myproject_compose.ui.theme.data.api.ApiService
import com.example.myproject_compose.ui.theme.data.model.CharacterResponse
import com.example.myproject_compose.ui.theme.data.model.LocationResponse
import retrofit2.Response

class Repository(private val apiService: ApiService) {

    suspend fun getCharacterById(id: Int) = apiService.getCharacterById(id)

    suspend fun getLocationById(id: Int) = apiService.getLocationById(id)

    suspend fun getCharacters(page: Int): CharacterResponse {
        return apiService.getCharacters(page)
    }

    suspend fun getLocations(page: Int): Response<LocationResponse> {
        val response = apiService.getLocations(page)
        if (!response.isSuccessful) {
            Log.e("Repository", "Failed to fetch locations: ${response.code()} ${response.message()}")
        }
        return response
    }

    suspend fun getEpisodes(page: Int): Response<EpisodeResponse> {
        return apiService.getEpisodes(page)
    }
}