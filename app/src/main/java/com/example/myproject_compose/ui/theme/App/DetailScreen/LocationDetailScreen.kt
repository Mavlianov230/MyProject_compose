package com.example.myproject_compose.ui.theme.App.DetailScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myproject_compose.ui.theme.App.ViewModel.LocationDetailViewModel
import org.koin.androidx.compose.koinViewModel
import com.example.myproject_compose.ui.theme.App.appBar.AppTopBar

@Composable
fun LocationDetailScreen(navController: NavController, locationId: Int) {
    val viewModel: LocationDetailViewModel = koinViewModel()
    val location = viewModel.location.collectAsState()

    LaunchedEffect(locationId) {
        viewModel.fetchLocationById(locationId)
    }

    location.value?.let { location ->
        Column {
            AppTopBar(
                navController = navController,
                title = location.name,
                canNavigateBack = true
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Name: ${location.name}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Type: ${location.type}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Dimension: ${location.dimension}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    } ?: run {
        AppTopBar(
            navController = navController,
            title = "Loading...",
            canNavigateBack = true
        )
        Text(text = "Loading...", style = MaterialTheme.typography.bodyMedium)
    }
}
