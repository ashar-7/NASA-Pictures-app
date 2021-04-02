package com.example.nasapictures

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasapictures.data.ImagesRepository
import kotlinx.coroutines.launch

class ImagesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ImagesRepository.getInstance()

    init {
        viewModelScope.launch {
            repository.load(application)
        }
    }
}
