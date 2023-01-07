package com.example.unsplashclient.presentation.search_photos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashclient.common.NetworkResponse
import com.example.unsplashclient.domain.use_case.SearchPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchPhotosViewModel @Inject constructor(
    private val searchPhotosUseCase: SearchPhotoUseCase
): ViewModel() {
    // privateなstate
    private val _state = mutableStateOf(SearchPhotosState())
    // Viewから参照するた目のstate
    val state: State<SearchPhotosState> = _state

    init {
        searchPhotos("tokyo")
    }

    fun searchPhotos(query: String) {
        // onEachの中身については状態変わるたびに呼ばれる
        searchPhotosUseCase(query).onEach { result ->
            when (result) {
                is NetworkResponse.Success -> {
                    _state.value = SearchPhotosState(
                        isLoading = false,
                        photos = result.data ?: emptyList()
                    )
                }
                is NetworkResponse.Failure -> {
                    _state.value = SearchPhotosState(
                        isLoading = false,
                        error = result.error
                    )
                }
                is NetworkResponse.Loading -> {
                    _state.value = SearchPhotosState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope) // launchしないとViewModelとした扱えない
    }

}