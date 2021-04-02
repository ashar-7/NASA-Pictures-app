package com.example.nasapictures.ui.details

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.nasapictures.ImagesViewModel

@Composable
fun ImageDetailsScreen(imageId: String, imagesViewModel: ImagesViewModel) {
    Text(imageId)
}
