package com.example.myproject_compose.ui.theme.App.repasitory

import com.example.myproject_compose.ui.theme.data.local.FavoriteCharacter
import com.example.myproject_compose.ui.theme.data.local.FavoriteCharacterDao
import kotlinx.coroutines.flow.Flow

class FavoriteCharacterRepository(private val favoriteCharacterDao: FavoriteCharacterDao) {

    fun getFavoriteCharacters(): Flow<List<FavoriteCharacter>> {
        return favoriteCharacterDao.getFavoriteCharacters()
    }

    suspend fun addFavorite(character: FavoriteCharacter) {
        favoriteCharacterDao.addToFavorites(character)
    }

    suspend fun removeFavorite(characterId: Int) {
        favoriteCharacterDao.removeFromFavorites(characterId)
    }
}