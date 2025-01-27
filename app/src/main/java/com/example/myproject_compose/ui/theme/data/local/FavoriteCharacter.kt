package com.example.myproject_compose.ui.theme.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_characters")
data class FavoriteCharacter(
    @PrimaryKey val id: Int,
    val name: String,
    val species: String,
    val image: String,
    val status: String,
    val isFavorite: Int = 0
)