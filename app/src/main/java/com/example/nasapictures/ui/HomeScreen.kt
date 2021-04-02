package com.example.nasapictures.ui

import androidx.appcompat.app.AppCompatDelegate
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

@Composable
fun HomeScreen(imagesViewModel: ImagesViewModel) {
    val isDarkTheme = isSystemInDarkTheme()

    Scaffold(
        topBar = {
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
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
        ) {
            when (val uiState = imagesViewModel.uiState) {
                is UIState.Idle -> {
                }
                is UIState.Loading -> {
                }
                is UIState.Success -> {
                }
                is UIState.Failure -> {
                }
            }
        }
    }
}
