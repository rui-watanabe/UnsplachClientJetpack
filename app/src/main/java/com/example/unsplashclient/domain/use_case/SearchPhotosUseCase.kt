package com.example.unsplashclient.domain.use_case

import com.example.unsplashclient.common.NetworkResponse
import com.example.unsplashclient.data.remote.toPhotos
import com.example.unsplashclient.domain.model.Photo
import com.example.unsplashclient.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchPhotosUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    //　View側からQuery（検索入力値）を受け取りその結果を返すメソッドを用意する
    // operator fun invokeで、SearchPhotoUseCase()()といった呼び出し方ができる
    // 返り値はList<Photo>
    // 通信状態を保持するようにしたいので、NetworkResponse<List<Photo>>
    // 通信の蘭族的な状態を返しネットワーク状態の監視ができるようにしたいのでFlowを使い、Flow<NetworkResponse<List<Photo>>>
    // var isBool: Bool = falseと同じ考え方
    operator fun invoke(query: String): Flow<NetworkResponse<List<Photo>>> = flow {
        try {
            // flowの関数をemitで呼び出す
            // flowのなかで通信状態を返したい場合はemitを使う
            // まずこのメソッドが呼び出されたら通信状態にしたい
            emit(NetworkResponse.Loading<List<Photo>>())
            val photos = repository.searchPhotos(query).toPhotos()
            emit(NetworkResponse.Success<List<Photo>>(photos))
        } catch (e: Exception) {
            emit(NetworkResponse.Failure<List<Photo>>(e.message.toString()))
        }

    }

}