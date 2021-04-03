package com.example.nasapictures.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nasapictures.R

@Composable
fun LoadingContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(42.dp))
    }
}

@Composable
fun ErrorContent() {
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
