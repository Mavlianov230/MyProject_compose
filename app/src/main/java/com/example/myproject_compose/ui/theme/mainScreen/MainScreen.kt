package com.example.myproject_compose.ui.theme.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myproject_compose.ui.theme.character.CharacterDetailScreen
import com.example.myproject_compose.ui.theme.character.CharactersScreen
import com.example.myproject_compose.ui.theme.data.appBar.AppBottomBar
import com.example.myproject_compose.ui.theme.data.appBar.AppTopBar
import com.example.myproject_compose.ui.theme.episode.EpisodesScreen
import com.example.myproject_compose.ui.theme.episode.EpisodesViewModel
import com.example.myproject_compose.ui.theme.favorite.FavoriteCharacterViewModel
import com.example.myproject_compose.ui.theme.favorite.FavoriteCharactersScreen
import com.example.myproject_compose.ui.theme.location.LocationDetailScreen
import com.example.myproject_compose.ui.theme.location.LocationsScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val episodesViewModel = koinViewModel<EpisodesViewModel>()

    Scaffold(
        topBar = { AppTopBar(navController) },
        bottomBar = { AppBottomBar(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)
            .background(Color.Cyan)) {
            NavHost(navController = navController, startDestination = "characters") {

                composable("characters") { CharactersScreen(navController) }
                composable("locations") { LocationsScreen(navController) }
                composable("episodes") {
                    EpisodesScreen(episodesViewModel)
                }
                composable("character_detail/{characterId}") { backStackEntry ->
                    val characterId =
                        backStackEntry.arguments?.getString("characterId")?.toIntOrNull()
                    if (characterId != null) {
                        CharacterDetailScreen(characterId)
                    }
                }
                composable("location_detail/{locationId}") { backStackEntry ->
                    val locationId =
                        backStackEntry.arguments?.getString("locationId")?.toIntOrNull()
                    if (locationId != null) {
                        LocationDetailScreen(locationId)
                    }
                }
                composable("favorites") {
                    val viewModel: FavoriteCharacterViewModel = koinViewModel()
                    FavoriteCharactersScreen(viewModel = viewModel)
                }
            }
        }
    }
}