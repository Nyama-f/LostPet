package com.example.lostpet.domain.repository.impl

import com.example.lostpet.data.api.ApiService
import com.example.lostpet.data.api.RetrofitBuilder
import com.example.lostpet.data.model.Pet
import com.example.lostpet.data.model.User
import com.example.lostpet.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow

class ApiRepositoryImpl(): ApiRepository {
    override fun getUsers(): Flow<List<User>> {
        TODO()
    }

    override fun getUser(userId: Int): Flow<User> {
        TODO("Not yet implemented")
    }

    override fun addUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun getPets(): Flow<List<Pet>> {
        TODO("Not yet implemented")
    }

    override fun getPet(petId: Int): Flow<Pet> {
        TODO("Not yet implemented")
    }

    override fun addPet(pet: Pet) {
        TODO("Not yet implemented")
    }

}