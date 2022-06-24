package com.example.lostpet.domain.useCases.impl

import com.example.lostpet.data.model.Pet
import com.example.lostpet.domain.repository.ApiRepository
import com.example.lostpet.domain.useCases.AddPetUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddPetUseCaseImpl @Inject constructor(
    private val apiRepository: ApiRepository
): AddPetUseCase {
    override fun invoke(pet: Pet) {
        apiRepository.addPet(pet = pet).flowOn(Dispatchers.IO)
    }
}