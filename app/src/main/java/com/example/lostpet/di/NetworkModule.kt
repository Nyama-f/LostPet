package com.example.lostpet.di

import com.example.lostpet.data.api.ApiService
import com.example.lostpet.domain.repository.ApiRepository
import com.example.lostpet.domain.repository.impl.ApiRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideApiService(): ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    companion object{
        private const val BASE_URL = "https://62a226f1cc8c0118ef5de9a6.mockapi.io/"
    }


}