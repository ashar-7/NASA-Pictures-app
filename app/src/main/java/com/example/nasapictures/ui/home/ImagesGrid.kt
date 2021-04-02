package com.example.nasapictures.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nasapictures.data.NASAImage
import com.example.nasapictures.ui.components.verticalGradientBackground
import com.google.accompanist.glide.GlideImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesGrid(images: List<NASAImage>, modifier: Modifier = Modifier) {
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
    Box(modifier = modifier.clip(RoundedCornerShape(8.dp))) {
        GlideImage(
            data = image.url,
            contentDescription = image.title,
            fadeIn = true,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalGradientBackground(
                    listOf(
                        Color.Transparent,
                        Color.Transparent,
                        Color.Black
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
