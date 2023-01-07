package com.example.unsplashclient.presentation.search_photos

import com.example.unsplashclient.domain.model.PhotoDetail

data class SearchPhotosState(
    val isLoading: Boolean = false,
    val photos: List<PhotoDetail> = emptyList(),
    val error: String? = null
)
