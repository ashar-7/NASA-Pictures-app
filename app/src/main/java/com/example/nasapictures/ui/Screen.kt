package com.example.nasapictures.ui

private const val ID_ARGUMENT_NAME = "id"

sealed class Screen(val route: String, val label: String) {
    object Home : Screen("home", "Home")
    object ImageDetails : Screen("image-details/{$ID_ARGUMENT_NAME}", "Details") {
        fun getRouteWithArgs(id: String) = "image-details/$id"

        const val idArgName = ID_ARGUMENT_NAME
    }
}
