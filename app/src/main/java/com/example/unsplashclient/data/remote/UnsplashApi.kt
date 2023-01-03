package com.example.unsplashclient.data.remote

import com.example.unsplashclient.common.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApi {
    @Headers("Authorization: Client-ID YOUR_ACCESS_KEY${Constants.API_KEY}")
    @GET("search/photos")
    //suspend付与で非同期にできる
    //queryというパラメータに文字列を設定する
    suspend fun searchPhotos(@Query("query") query: String): SearchPhotosResultDto

    @Headers("Authorization: Client-ID YOUR_ACCESS_KEY${Constants.API_KEY}")
    @GET("id/{id}") //idはパラメータとして受けとる
    suspend fun getPhotoById(@Path("id") photoId: String): PhotoDetailsDto
}