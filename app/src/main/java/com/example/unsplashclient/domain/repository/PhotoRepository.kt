package com.example.unsplashclient.domain.repository

import com.example.unsplashclient.data.remote.PhotoDetailsDto
import com.example.unsplashclient.data.remote.SearchPhotosResultDto

// dataのinterfaceを参照するとそちらに依存してしまうので、domainで同様のinterfaceを作成
interface PhotoRepository {
    suspend fun searchPhotos(query: String): SearchPhotosResultDto
    suspend fun getPhotoById(id: String):  PhotoDetailsDto
}