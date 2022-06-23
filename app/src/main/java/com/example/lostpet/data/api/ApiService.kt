package com.example.lostpet.data.api

import com.example.lostpet.data.model.Pet
import com.example.lostpet.data.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") userId: Int): User

    @POST("user")
    suspend fun addUser(@Body user: User)


    @GET("pets")
    suspend fun getPets(): List<Pet>

    @GET("pets/{id}")
    suspend fun getPet(@Path("id") petId: Int): Pet

    @POST("pet")
    suspend fun addPet(@Body pet: Pet)

}