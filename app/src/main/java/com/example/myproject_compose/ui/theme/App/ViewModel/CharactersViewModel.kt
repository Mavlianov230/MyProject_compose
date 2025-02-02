package com.example.myproject_compose.ui.theme.App.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.myproject_compose.ui.theme.App.character.toCharacterResponse
import com.example.myproject_compose.ui.theme.App.character.toFavoriteCharacter
import com.example.myproject_compose.ui.theme.App.Response.CharacterResponse
import com.example.myproject_compose.ui.theme.App.paging.CharacterPagingSource
import com.example.myproject_compose.ui.theme.App.repasitory.FavoriteCharacterRepository
import com.example.myproject_compose.ui.theme.App.repasitory.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val repository: Repository,
    private val favoriteCharacterRepository: FavoriteCharacterRepository
) : ViewModel() {

    val characters = Pager(PagingConfig(pageSize = 20)) {
        CharacterPagingSource(repository)
    }.flow.cachedIn(viewModelScope)

    private val _favoriteCharacters =
        MutableStateFlow<List<CharacterResponse.Character>>(emptyList())
    val favoriteCharacters: StateFlow<List<CharacterResponse.Character>> get() = _favoriteCharacters

    init {
        loadFavoriteCharacters()
    }

    private fun loadFavoriteCharacters() {
        viewModelScope.launch {
            favoriteCharacterRepository.getFavoriteCharacters().collect { favorites ->
                _favoriteCharacters.value = favorites.map { it.toCharacterResponse() }
            }
        }
    }

    fun toggleFavorite(character: CharacterResponse.Character?) {
        viewModelScope.launch {

            val favoriteCharacter = character?.toFavoriteCharacter()

            if (_favoriteCharacters.value.any { it.id == character?.id }) {

                favoriteCharacter?.id?.let { favoriteCharacterRepository.removeFavorite(it) }
            } else {
                if (favoriteCharacter != null) {
                    favoriteCharacterRepository.addFavorite(favoriteCharacter)
                }
            }

            loadFavoriteCharacters()
        }
    }
}