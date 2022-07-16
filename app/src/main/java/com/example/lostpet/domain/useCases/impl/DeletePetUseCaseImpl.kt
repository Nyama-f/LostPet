package com.example.lostpet.domain.useCases.impl

import android.util.Log
import com.example.lostpet.data.model.Pet
import com.example.lostpet.domain.repository.ApiRepository
import com.example.lostpet.domain.useCases.DeletePetUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeletePetUseCaseImpl @Inject constructor(
    private val apiRepository: ApiRepository
): DeletePetUseCase {

    override suspend fun invoke(userId: Int, petId: Int) {
        withContext(Dispatchers.IO){
            apiRepository.deletePet(userId, petId)
        }
    }
}