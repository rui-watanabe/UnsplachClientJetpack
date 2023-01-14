package com.example.unsplashclient.di

import android.provider.ContactsContract.Contacts.Photo
import com.example.unsplashclient.common.Constants.BASE_URL
import com.example.unsplashclient.data.remote.UnsplashApi
import com.example.unsplashclient.data.repository.PhotoRepositoryImpl
import com.example.unsplashclient.domain.repository.PhotoRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUnsplashApi(): UnsplashApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )//JSONからKotlinオブジェクトに変換するためのもの
            .build()
            .create(UnsplashApi::class.java)//引数にAPIのインターフェースが必要
    }

    @Provides
    @Singleton
    fun providePhotoRepository(api: UnsplashApi): PhotoRepository {
        return PhotoRepositoryImpl(api)
    }
}