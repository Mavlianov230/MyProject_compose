package com.example.myproject_compose.ui.theme.data.appBar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AppBottomBar(
    navController: NavController,
) {
    NavigationBar {
        val currentRoute = navController.currentBackStackEntry?.destination?.route

        val items = listOf(
            BottomBarItem("Characters", "characters", Icons.Filled.Person),
            BottomBarItem("Locations", "locations", Icons.Filled.LocationOn),
            BottomBarItem("Episodes", "episodes", Icons.Filled.List),
            BottomBarItem("Favorites", "favorites", Icons.Filled.Star)
        )

        items.forEach { item ->
            val isSelected = currentRoute == item.route
            val iconSize by animateFloatAsState(
                targetValue = if (isSelected) 32f else 24f
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(iconSize.dp)
                    )
                },
                label = { Text(item.label) },
                selected = isSelected,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(item.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}

data class BottomBarItem(
    val label: String,
    val route: String,
    val icon: ImageVector
)
