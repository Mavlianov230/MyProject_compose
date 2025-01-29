import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
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
import com.google.accompanist.navigation.animation.*
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    val navController = rememberAnimatedNavController()
    val episodesViewModel = koinViewModel<EpisodesViewModel>()

    Scaffold(
        topBar = {
            AppTopBar(
                navController = navController,
                title = "Rick and Morty",
                canNavigateBack = false
            )
        },
        bottomBar = { AppBottomBar(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.Black)
        ) {
            AnimatedNavHost(
                navController = navController,
                startDestination = "characters"
            ) {
                composable(
                    "characters",
                    enterTransition = { fadeIn(animationSpec = tween(1000)) },
                    exitTransition = { fadeOut(animationSpec = tween(500)) }
                ) {
                    CharactersScreen(navController = navController, onScrollChange = { })
                }

                composable(
                    "character_detail/{characterId}",
                    enterTransition = {
                        scaleIn(initialScale = 0.8f, animationSpec = tween(1000)) +
                                fadeIn(animationSpec = tween(1000))
                    },
                    exitTransition = {
                        scaleOut(targetScale = 1.2f, animationSpec = tween(500)) +
                                fadeOut(animationSpec = tween(500))
                    }
                ) { backStackEntry ->
                    val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull()
                    characterId?.let {
                        CharacterDetailScreen(navController = navController, characterId = it)
                    }
                }

                composable(
                    "locations",
                    enterTransition = { fadeIn(animationSpec = tween(1000)) },
                    exitTransition = { fadeOut(animationSpec = tween(500)) }
                ) {
                    LocationsScreen(navController = navController)
                }

                composable(
                    "episodes",
                    enterTransition = { fadeIn(animationSpec = tween(1000)) },
                    exitTransition = { fadeOut(animationSpec = tween(500)) }
                ) {
                    EpisodesScreen(episodesViewModel)
                }

                composable(
                    "location_detail/{locationId}",
                    enterTransition = { fadeIn(animationSpec = tween(1000)) },
                    exitTransition = { fadeOut(animationSpec = tween(500)) }
                ) { backStackEntry ->
                    val locationId = backStackEntry.arguments?.getString("locationId")?.toIntOrNull()
                    locationId?.let {
                        LocationDetailScreen(navController = navController, locationId = it)
                    }
                }

                composable(
                    "favorites",
                    enterTransition = { fadeIn(animationSpec = tween(1000)) },
                    exitTransition = { fadeOut(animationSpec = tween(500)) }
                ) {
                    val viewModel: FavoriteCharacterViewModel = koinViewModel()
                    FavoriteCharactersScreen(viewModel = viewModel)
                }
            }
        }
    }
}
