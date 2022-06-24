package com.example.lostpet.domain.repository

import com.example.lostpet.data.model.Pet
import com.example.lostpet.data.model.User
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    fun getUsers(): Flow<List<User>>

    fun getUser(userId: Int): Flow<User>

    fun addUser(user: User): Flow<Unit>

    fun getPets(): Flow<List<Pet>>

    fun getPet(petId: Int): Flow<Pet>

    fun addPet(pet: Pet): Flow<Unit>
}