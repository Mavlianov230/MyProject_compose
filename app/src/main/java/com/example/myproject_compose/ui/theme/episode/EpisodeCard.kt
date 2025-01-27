package com.example.myproject_compose.ui.theme.episode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myproject_compose.ui.theme.data.model.Episode

@Composable
fun EpisodeCard(episode: Episode) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Episode: ${episode.name}")
            Text(text = "Air Date: ${episode.airDate}")
        }
    }
}