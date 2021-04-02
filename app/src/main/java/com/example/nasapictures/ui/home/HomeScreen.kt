package com.example.nasapictures.ui.home

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nasapictures.ImagesViewModel
import com.example.nasapictures.R
import com.example.nasapictures.UIState
import com.example.nasapictures.ui.Screen

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
            Crossfade(imagesViewModel.uiState) { uiState ->
                when (uiState) {
                    is UIState.Idle -> {
                    }
                    is UIState.Loading -> {
                        LoadingContent()
                    }
                    is UIState.Success -> {
                        ImagesGrid(uiState.data)
                    }
                    is UIState.Failure -> {
                        ErrorContent()
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(42.dp))
    }
}

@Composable
private fun ErrorContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(painterResource(R.drawable.robot_404), null)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                Text(
                    stringResource(R.string.images_error_heading),
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )
            }
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                Text(
                    stringResource(R.string.images_error_description),
                    style = MaterialTheme.typography.caption.copy(fontSize = 13.sp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
