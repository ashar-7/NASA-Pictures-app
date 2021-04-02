package com.example.nasapictures.ui

sealed class Screen(val route: String, val label: String) {
    object Home : Screen("home", "Home")
    object ImageDetails : Screen("image-details", "Details")
}
