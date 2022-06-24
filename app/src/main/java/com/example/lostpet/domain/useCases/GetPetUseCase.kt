package com.example.lostpet.domain.useCases

import com.example.lostpet.data.model.Pet
import kotlinx.coroutines.flow.Flow

interface GetPetUseCase {

    operator fun invoke(petId: Int): Flow<Pet>

}