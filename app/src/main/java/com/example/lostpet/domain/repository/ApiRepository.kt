package com.example.lostpet.domain.repository

import com.example.lostpet.data.model.EditData
import com.example.lostpet.data.model.Pet
import com.example.lostpet.data.model.User
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    fun getUsers(): Flow<List<User>>

    fun getUser(userId: Int): Flow<User>

    suspend fun deleteUser(userId: Int)

    fun editUser(userId: Int, editData: EditData): Flow<User>

    suspend fun addUser(user: User)

    fun getPets(userId: Int): Flow<List<Pet>>

    fun getPet(petId: Int): Flow<Pet>

    suspend fun addPet(pet: Pet, userId: Int)

    suspend fun deletePet(userId: Int, petId: Int)
}