package com.example.lostpet.data.api

import com.example.lostpet.data.model.Pet
import com.example.lostpet.data.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("user")
    suspend fun getUsers(): List<User>

    @GET("user/{id}")
    suspend fun getUser(@Path("id") userId: Int): User

    @POST("user")
    suspend fun addUser(@Body user: User)


    @GET("pet")
    suspend fun getPets(): List<Pet>

    @GET("pet/{id}")
    suspend fun getPet(@Path("id") petId: Int): Pet

    @POST("pet")
    suspend fun addPet(@Body pet: Pet)

}