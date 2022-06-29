package com.example.lostpet.domain.repository.impl

import android.util.Log
import com.example.lostpet.data.api.ApiService
import com.example.lostpet.data.model.Pet
import com.example.lostpet.data.model.User
import com.example.lostpet.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ApiRepository {
    override fun getUsers(): Flow<List<User>>  = flow{
        val api = apiService.getUsers()
        emit(api)
    }

    override fun getUser(userId: Int): Flow<User> = flow {
        val api = apiService.getUser(userId = userId)
        emit(api)
    }

    override suspend fun addUser(user: User){
       // Log.d("UserNyama2", "_")
        apiService.addUser(user = user)
    }

    override fun getPets(): Flow<List<Pet>> = flow {
        val api = apiService.getPets()
        emit(api)
    }

    override fun getPet(petId: Int): Flow<Pet> = flow {
        val api = apiService.getPet(petId = petId)
        emit(api)
    }

    override suspend fun addPet(pet: Pet, userId: Int){
        apiService.addPet(pet = pet, userId = userId)
        Log.d("UserNyama1", "APIRepositoryImpl")
    }

}