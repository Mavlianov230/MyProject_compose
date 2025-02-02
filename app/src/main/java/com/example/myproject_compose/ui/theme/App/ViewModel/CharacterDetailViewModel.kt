package com.example.myproject_compose.ui.theme.App.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myproject_compose.ui.theme.App.Response.CharacterResponse
import com.example.myproject_compose.ui.theme.App.repasitory.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(private val repository: Repository) : ViewModel() {

    private val _character = MutableStateFlow<CharacterResponse.Character?>(null)
    val character: StateFlow<CharacterResponse.Character?> get() = _character

    fun fetchCharacterById(id: Int) {
        viewModelScope.launch {
            val response = repository.getCharacterById(id)
            if (response.isSuccessful) {
                _character.value = response.body()
            }
        }
    }
}