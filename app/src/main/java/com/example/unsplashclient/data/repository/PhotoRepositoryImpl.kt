package com.example.unsplashclient.data.repository

import com.example.unsplashclient.data.remote.PhotoDetailsDto
import com.example.unsplashclient.data.remote.SearchPhotosResultDto
import com.example.unsplashclient.data.remote.UnsplashApi
import com.example.unsplashclient.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val api: UnsplashApi
    ) : PhotoRepository {
    override suspend fun searchPhotos(query: String): SearchPhotosResultDto {
        return api.searchPhotos(query)
    }

    override suspend fun getPhotoById(id: String): PhotoDetailsDto {
        return api.getPhotoById(id)
    }
}
