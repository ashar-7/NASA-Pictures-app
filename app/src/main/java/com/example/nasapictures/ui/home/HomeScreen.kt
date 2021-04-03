package com.example.nasapictures.ui.home

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.nasapictures.ImagesViewModel
import com.example.nasapictures.R
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
    val isDarkTheme = isSystemInDarkTheme()
    TopAppBar(
        title = { Text(Screen.Home.label) },
        actions = {
            IconButton(onClick = {
                AppCompatDelegate.setDefaultNightMode(
                    when {
                        isDarkTheme -> AppCompatDelegate.MODE_NIGHT_NO
                        else -> AppCompatDelegate.MODE_NIGHT_YES
                    }
                )
            }) {
                Icon(
                    when {
                        isDarkTheme -> Icons.Default.LightMode
                        else -> Icons.Default.DarkMode
                    },
                    contentDescription = stringResource(R.string.toggle_night_mode)
                )
            }
        }
    )
}
