package com.example.myproject_compose.ui.theme.data.api

import com.example.myproject_compose.ui.theme.App.Response.CharacterResponse
import com.example.myproject_compose.ui.theme.App.Response.EpisodeResponse
import com.example.myproject_compose.ui.theme.App.Response.LocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("location")
    suspend fun getLocations(@Query("page") page: Int): Response<LocationResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(@retrofit2.http.Path("id") id: Int): Response<CharacterResponse.Character>

    @GET("location/{id}")
    suspend fun getLocationById(@retrofit2.http.Path("id") id: Int): Response<LocationResponse.Location>

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterResponse

    @GET("episode")
    suspend fun getEpisodes(@Query("page") page: Int): Response<EpisodeResponse>
}