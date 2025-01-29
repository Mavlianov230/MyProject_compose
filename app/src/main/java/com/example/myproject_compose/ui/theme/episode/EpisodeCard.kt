package com.example.myproject_compose.ui.theme.episode

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myproject_compose.ui.theme.data.model.Episode

@Composable
fun EpisodeCard(episode: Episode) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                isExpanded = !isExpanded
            }
            .animateContentSize(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Episode: ${episode.name}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Air Date: ${episode.airDate}", style = MaterialTheme.typography.bodySmall)

            if (isExpanded) {
                Text(
                    text = "More details about the episode...",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}
