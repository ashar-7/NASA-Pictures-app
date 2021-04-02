package com.example.nasapictures

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.nasapictures.ui.Root
import com.example.nasapictures.ui.theme.NASAPicturesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NASAPicturesTheme {
                Root()
            }
        }
    }
}
