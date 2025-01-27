package com.example.myproject_compose.ui.theme.character

import com.example.myproject_compose.ui.theme.data.local.FavoriteCharacter
import com.example.myproject_compose.ui.theme.data.model.CharacterResponse

fun CharacterResponse.Character.toFavoriteCharacter(): FavoriteCharacter {
    return FavoriteCharacter(
        id = this.id,
        name = this.name,
        species = this.species,
        image = this.image,
        status = this.status,
        isFavorite = 1
    )
}

fun FavoriteCharacter.toCharacterResponse(): CharacterResponse.Character {
    return CharacterResponse.Character(
        id = this.id,
        name = this.name,
        species = this.species,
        status = this.status,
        image = this.image
    )
}