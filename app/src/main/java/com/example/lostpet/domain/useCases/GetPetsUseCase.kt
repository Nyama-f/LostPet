package com.example.lostpet.domain.useCases

import com.example.lostpet.data.model.Pet
import kotlinx.coroutines.flow.Flow

interface GetPetsUseCase {

    operator fun invoke(userId: Int): Flow<List<Pet>>

}