package com.example.nasapictures.ui

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nasapictures.ImagesViewModel
import com.example.nasapictures.R
import com.example.nasapictures.UIState
import com.example.nasapictures.data.NASAImage
import com.google.accompanist.glide.GlideImage

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
                    ImagesGrid(uiState.data)
                }
                is UIState.Failure -> {
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ImagesGrid(images: List<NASAImage>, modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier = modifier) {
        val columns = if (maxWidth < 600.dp) 2 else if (maxWidth < 900.dp) 4 else 6
        LazyVerticalGrid(cells = GridCells.Fixed(columns)) {
            items(images) { item ->
                ImagesGridItem(
                    Modifier
                        .fillMaxSize()
                        .aspectRatio(0.7f)
                        .padding(2.dp),
                    image = item
                )
            }
        }
    }
}

@Composable
private fun ImagesGridItem(modifier: Modifier, image: NASAImage) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
    ) {
        GlideImage(
            data = image.url,
            contentDescription = image.title,
            fadeIn = true,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        var size by remember { mutableStateOf(IntSize.Zero) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .onSizeChanged { size = it }
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Transparent, Color.Black),
                        startY = 0f,
                        endY = size.height.toFloat()
                    )
                )
                .padding(8.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                image.title,
                style = MaterialTheme.typography.subtitle2.copy(fontSize = 15.sp),
                color = Color.White,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
