package com.example.myproject_compose.ui.theme.repasitory

import com.example.myproject_compose.ui.theme.data.model.Episode


interface EpisodesRepository {
    suspend fun getNextPageEpisodes(): List<Episode>
}