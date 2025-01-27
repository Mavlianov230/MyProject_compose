package com.example.myproject_compose.ui.theme.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.myproject_compose.ui.theme.paging.LocationsPagingSource
import com.example.myproject_compose.ui.theme.repasitory.Repository

class LocationsViewModel(private val repository: Repository) : ViewModel() {

    val locations = Pager(PagingConfig(pageSize = 20)) {
        LocationsPagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}