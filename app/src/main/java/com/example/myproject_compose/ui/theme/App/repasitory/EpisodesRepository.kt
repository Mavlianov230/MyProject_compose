package com.example.myproject_compose.ui.theme.App.repasitory

import com.example.myproject_compose.ui.theme.App.Response.Episode


interface EpisodesRepository {
    suspend fun getNextPageEpisodes(): List<Episode>
}