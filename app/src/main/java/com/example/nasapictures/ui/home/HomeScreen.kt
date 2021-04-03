package com.example.nasapictures.ui.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.nasapictures.ImagesViewModel
import com.example.nasapictures.UIState
import com.example.nasapictures.data.NASAImage
import com.example.nasapictures.ui.Screen
import com.example.nasapictures.ui.components.ErrorContent
import com.example.nasapictures.ui.components.LoadingContent

@Composable
fun HomeScreen(imagesViewModel: ImagesViewModel, onImageSelected: (NASAImage) -> Unit) {
    Scaffold(
        topBar = { HomeTopBar() }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            Crossfade(imagesViewModel.uiState) { uiState ->
                when (uiState) {
                    is UIState.Idle -> {
                    }
                    is UIState.Loading -> LoadingContent()
                    is UIState.Success -> {
                        ImagesGrid(uiState.data, onImageSelected = onImageSelected)
                    }
                    is UIState.Failure -> ErrorContent()
                }
            }
        }
    }
}

@Composable
private fun HomeTopBar() {
    TopAppBar(
        title = { Text(Screen.Home.label) },
        backgroundColor = MaterialTheme.colors.primary
    )
}
