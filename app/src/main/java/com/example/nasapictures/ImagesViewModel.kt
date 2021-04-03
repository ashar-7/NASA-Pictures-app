package com.example.nasapictures

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasapictures.data.ImagesRepository
import com.example.nasapictures.data.NASAImage
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ImagesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ImagesRepository.getInstance()
    var uiState by mutableStateOf<UIState<List<NASAImage>>>(UIState.Idle())

    init {
        viewModelScope.launch {
            uiState = UIState.Loading()
            uiState = when (val data = repository.load(application)) {
                null -> {
                    UIState.Failure()
                }
                else -> {
                    UIState.Success(data)
                }
            }
        }
    }

    fun getImage(id: String) = flow {
        when (val state = uiState) {
            // Treating title as id
            is UIState.Success -> emit(state.data.find { it.title == id })
            else -> emit(null)
        }
    }
}
