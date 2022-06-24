package com.example.lostpet.data.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitBuilder {

     fun provideApiService(): ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    companion object{
        private const val BASE_URL = "https://62a226f1cc8c0118ef5de9a6.mockapi.io/"
    }
}