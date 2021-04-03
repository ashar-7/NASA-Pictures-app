package com.example.nasapictures.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.nasapictures.ImagesViewModel
import com.example.nasapictures.ui.details.ImageDetailsScreen
import com.example.nasapictures.ui.home.HomeScreen

@Composable
fun Root() {
    val navController = rememberNavController()
    val imagesViewModel = viewModel<ImagesViewModel>()

    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(imagesViewModel, onImageSelected = { image ->
                navController.navigate(Screen.ImageDetails.getRouteWithArgs(image.title))
            })
        }

        composable(Screen.ImageDetails.route) {
            it.arguments?.getString(Screen.ImageDetails.idArgName)?.let { id ->
                ImageDetailsScreen(id, imagesViewModel, onNavigateUp = {
                    navController.navigateUp()
                })
            }
        }
    }
}
