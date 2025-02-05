package com.example.myproject_compose.ui.theme.App.character

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myproject_compose.ui.theme.App.Response.CharacterResponse

@Composable
fun CharacterCard(character: CharacterResponse.Character?, onClick: () -> Unit, isFavorite: Boolean, onFavoriteClick: () -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                isExpanded = !isExpanded
                onClick()
            }
            .animateContentSize(),
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = character?.image,
                contentDescription = character?.name,
                modifier = Modifier
                    .size(if (isExpanded) 128.dp else 84.dp)
                    .padding(end = 16.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                if (character != null) {
                    Text(text = character.name, style = MaterialTheme.typography.bodyLarge)
                }
                Text(
                    text = "${character?.species} - ${character?.status}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CharacterCardPreview() {
    CharacterCard(character = null, onClick = {}, isFavorite = false, onFavoriteClick = {})
}
