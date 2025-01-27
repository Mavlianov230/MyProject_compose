package com.example.myproject_compose.ui.theme.data.model

data class LocationResponse(
    val results: List<Location>
) {
    data class Location(
        val id: Int,
        val name: String,
        val type: String,
        val dimension: String,
        val imageUrl: String?
    )
}