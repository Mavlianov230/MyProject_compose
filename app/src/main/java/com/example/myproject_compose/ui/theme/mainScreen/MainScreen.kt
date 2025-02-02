import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myproject_compose.ui.theme.App.DetailScreen.CharacterDetailScreen
import com.example.myproject_compose.ui.theme.App.Screen.CharactersScreen
import com.example.myproject_compose.ui.theme.App.appBar.AppBottomBar
import com.example.myproject_compose.ui.theme.App.appBar.AppTopBar
import com.example.myproject_compose.ui.theme.App.Screen.EpisodesScreen
import com.example.myproject_compose.ui.theme.App.ViewModel.EpisodesViewModel
import com.example.myproject_compose.ui.theme.App.ViewModel.FavoriteCharacterViewModel
import com.example.myproject_compose.ui.theme.App.Screen.FavoriteCharactersScreen
import com.example.myproject_compose.ui.theme.App.DetailScreen.LocationDetailScreen
import com.example.myproject_compose.ui.theme.App.Screen.LocationsScreen
import com.google.accompanist.navigation.animation.*
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    val navController = rememberAnimatedNavController()
    val episodesViewModel = koinViewModel<EpisodesViewModel>()
    var isBottomBarVisible by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            AppTopBar(
                navController = navController,
                title = "Rick and Morty",
                canNavigateBack = false
            )
        },
        bottomBar = {

            if (isBottomBarVisible) {
                AppBottomBar(navController)
            }
        }
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
                    enterTransition = { fadeIn(animationSpec = tween(2000)) },
                    exitTransition = { fadeOut(animationSpec = tween(500)) }
                ) {
                    CharactersScreen(
                        navController = navController,
                        onScrollChange = { isScrollingUp ->
                            isBottomBarVisible = isScrollingUp
                        }
                    )
                }

                composable(
                    "locations",
                    enterTransition = { fadeIn(animationSpec = tween(2000)) },
                    exitTransition = { fadeOut(animationSpec = tween(500)) }
                ) {
                    LocationsScreen(navController = navController)
                }

                composable(
                    "episodes",
                    enterTransition = { fadeIn(animationSpec = tween(2000)) },
                    exitTransition = { fadeOut(animationSpec = tween(500)) }
                ) {
                    EpisodesScreen(episodesViewModel)
                }

                composable(
                    "location_detail/{locationId}",
                    enterTransition = { fadeIn(animationSpec = tween(2000)) },
                    exitTransition = { fadeOut(animationSpec = tween(500)) }
                ) { backStackEntry ->
                    val locationId =
                        backStackEntry.arguments?.getString("locationId")?.toIntOrNull()
                    locationId?.let {
                        LocationDetailScreen(navController = navController, locationId = it)
                    }
                }

                composable(
                    "favorites",
                    enterTransition = { fadeIn(animationSpec = tween(2000)) },
                    exitTransition = { fadeOut(animationSpec = tween(500)) }
                ) {
                    val viewModel: FavoriteCharacterViewModel = koinViewModel()
                    FavoriteCharactersScreen(viewModel = viewModel)
                }
            }
        }
    }
}