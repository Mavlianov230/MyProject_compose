package com.example.myproject_compose.ui.theme.App.Screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myproject_compose.ui.theme.App.appBar.LoadMoreButton
import com.example.myproject_compose.ui.theme.App.episode.EpisodeCard
import com.example.myproject_compose.ui.theme.App.ViewModel.EpisodesViewModel

@Composable
fun EpisodesScreen(viewModel: EpisodesViewModel = viewModel()) {

    val episodes = viewModel.episodes.collectAsState().value

    LazyColumn {
        items(episodes.size) { episode ->
            EpisodeCard(episodes[episode])
        }
        item {
            LoadMoreButton(
                onClick = { viewModel.loadMoreEpisodes() },
            )
        }
    }
}