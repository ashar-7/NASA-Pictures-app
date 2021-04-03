package com.example.nasapictures.ui.details

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.nasapictures.ImagesViewModel
import com.example.nasapictures.R
import com.example.nasapictures.data.NASAImage
import com.example.nasapictures.ui.components.verticalGradientBackground
import com.google.accompanist.glide.GlideImage

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ImageDetailsScreen(
    imageId: String,
    imagesViewModel: ImagesViewModel,
    onNavigateUp: () -> Unit
) {
    val image by imagesViewModel.getImage(imageId).collectAsState(null)

    Surface(color = Color.Black, contentColor = Color.White) {
        AnimatedVisibility(
            visible = image != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            image?.let { image ->
                ImageDetails(image, onNavigateUp = onNavigateUp)
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ImageDetails(image: NASAImage, onNavigateUp: () -> Unit) {
    var metadataVisible by remember { mutableStateOf(true) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                onClick = { metadataVisible = !metadataVisible },
                interactionSource = MutableInteractionSource(),
                indication = null
            )
    ) {
        GlideImage(
            data = image.url,
            contentDescription = image.title,
            fadeIn = true,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        AnimatedVisibility(
            visible = metadataVisible,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(500)),
            modifier = Modifier.align(Alignment.BottomStart)
        ) {
            Box {
                ImageMetadata(image)

                IconButton(
                    onClick = onNavigateUp,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(2.dp)
                ) {
                    Icon(Icons.Default.Close, contentDescription = stringResource(R.string.close))
                }
            }
        }
    }
}

@Composable
fun ImageMetadata(image: NASAImage) {
    var showMore by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalGradientBackground(
                0f to Color.Black.copy(alpha = 0.7f),
                0.2f to Color.Transparent,
                animateFloatAsState(if (showMore) 0.5f else 0.8f).value
                    to Color.Black.copy(alpha = 0.7f),
                1f to Color.Black
            )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomStart)
                .animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(image.title, style = MaterialTheme.typography.h6)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                val copyrightString =
                    if (image.copyright != null) "${image.copyright}, " else ""
                Text(
                    "$copyrightString${image.date}",
                    style = MaterialTheme.typography.caption
                )
            }

            Text(
                image.explanation,
                style = MaterialTheme.typography.caption,
                maxLines = if (showMore) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis
            )
            TextButton(
                onClick = { showMore = !showMore },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = LocalContentColor.current
                )
            ) {
                Text(
                    "Show ${if (showMore) "less" else "more"}",
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}
