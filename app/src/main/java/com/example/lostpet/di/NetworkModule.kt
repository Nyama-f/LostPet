package com.example.lostpet.di

import android.util.Log
import com.example.lostpet.data.api.ApiService
import com.example.lostpet.domain.repository.ApiRepository
import com.example.lostpet.domain.repository.impl.ApiRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideApiService(): ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client.build())
        .build()
        .create(ApiService::class.java)

    companion object{
        private const val BASE_URL = "https://62a226f1cc8c0118ef5de9a6.mockapi.io/"
        private var interceptor =
                HttpLoggingInterceptor { message -> Log.d("BD", message) }
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
        private val client = OkHttpClient.Builder().addInterceptor(interceptor)
    }

}