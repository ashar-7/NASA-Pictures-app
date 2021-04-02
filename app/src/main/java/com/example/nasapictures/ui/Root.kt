package com.example.nasapictures.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nasapictures.ImagesViewModel

@Composable
fun Root() {
    val navController = rememberNavController()
    val imagesViewModel = viewModel<ImagesViewModel>()

    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(imagesViewModel)
        }

        composable(Screen.ImageDetails.route) {
            ImageDetailsScreen(imagesViewModel)
        }
    }
}
