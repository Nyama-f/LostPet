package com.example.lostpet.domain.useCases.impl


import com.example.lostpet.data.model.Pet
import com.example.lostpet.domain.repository.ApiRepository
import com.example.lostpet.domain.useCases.AddPetUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddPetUseCaseImpl @Inject constructor(
    private val apiRepository: ApiRepository
): AddPetUseCase {
    override suspend  fun invoke(pet: Pet, userId: Int) {
        withContext(Dispatchers.IO){
            apiRepository.addPet(pet = pet, userId = userId)
        }
    }
}