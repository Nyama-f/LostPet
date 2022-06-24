package com.example.lostpet.domain.useCases.impl

import com.example.lostpet.data.model.Pet
import com.example.lostpet.domain.repository.ApiRepository
import com.example.lostpet.domain.useCases.GetPetUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPetUseCaseImpl @Inject constructor(
    private val apiRepository: ApiRepository
): GetPetUseCase {
    override fun invoke(petId: Int): Flow<Pet> {
        return apiRepository.getPet(petId = petId).flowOn(Dispatchers.IO)
    }
}