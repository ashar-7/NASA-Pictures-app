package com.example.nasapictures

sealed class UIState<T> {
    class Idle<T> : UIState<T>()
    class Loading<T> : UIState<T>()
    class Success<T>(val data: T) : UIState<T>()
    class Failure<T> : UIState<T>()
}
