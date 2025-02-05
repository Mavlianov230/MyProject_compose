package com.example.myproject_compose.ui.theme.App.Response

import com.google.gson.annotations.SerializedName

data class EpisodeResponse(
    val results: List<Episode>
)

data class Episode(
    val id: Int,
    val name: String,
    @SerializedName("air_date")
    val airDate: String,
    val episode: String,
    val characters: List<String>
)