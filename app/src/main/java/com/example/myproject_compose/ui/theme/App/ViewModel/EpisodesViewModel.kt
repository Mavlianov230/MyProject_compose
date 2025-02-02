package com.example.myproject_compose.ui.theme.App.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myproject_compose.ui.theme.App.Response.Episode
import com.example.myproject_compose.ui.theme.App.repasitory.EpisodesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EpisodesViewModel(private val repository: EpisodesRepository) : ViewModel() {

    private val _episodes = MutableStateFlow(mutableListOf<Episode>())
    val episodes: StateFlow<List<Episode>> = _episodes
    private val _loading = MutableStateFlow(false)

    init {
        loadMoreEpisodes()
    }

    fun loadMoreEpisodes() {
        viewModelScope.launch {
            _loading.value = true

            try {
                val newEpisodes = repository.getNextPageEpisodes()
                _episodes.value.addAll(newEpisodes)
            } catch (e: Exception) {
            } finally {
                _loading.value = false
            }
        }
    }
}