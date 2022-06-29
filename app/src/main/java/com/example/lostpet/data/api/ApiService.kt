package com.example.lostpet.data.api

import com.example.lostpet.data.model.Pet
import com.example.lostpet.data.model.User
import retrofit2.http.*

interface ApiService {

    @GET("user")
    suspend fun getUsers(): List<User>

    @GET("user/{id}")
    suspend fun getUser(@Path("id") userId: Int): User

    @POST("user")
    suspend fun addUser(@Body user: User)

    @PUT("user/{id}")
    suspend fun editUser(@Path("id") userId: Int): User

    @DELETE("user/{id}")
    suspend fun deleteUser(@Path("id") userId: Int): User


    @GET("user/{id}/mark")
    suspend fun getPets(@Path("id") userId: Int): List<Pet>

    @GET("mark/{id}")
    suspend fun getPet(@Path("id") petId: Int): Pet

    @POST("user/{id}/mark")
    suspend fun addPet(@Path("id") userId: Int, @Body pet: Pet)

    @PUT("user/{userId}/mark/{markId}")
    suspend fun editPet(@Path("userId") userId: Int, @Path("markId") petId: Int): Pet

    @DELETE("user/{id}/mark/{id}")
    suspend fun deletePet(@Path("id") petId: Int): Pet

}