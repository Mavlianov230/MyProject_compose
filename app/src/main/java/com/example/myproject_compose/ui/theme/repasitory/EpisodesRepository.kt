package com.example.myproject_compose.ui.theme.repasitory

import com.example.homework_jc.data.model.Episode

interface EpisodesRepository {
    suspend fun getNextPageEpisodes(): List<Episode>
}