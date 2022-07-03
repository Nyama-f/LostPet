package com.example.lostpet.domain.useCases

import com.example.lostpet.data.model.Pet
import kotlinx.coroutines.flow.Flow

interface DeletePetUseCase {
     suspend operator fun invoke(userId: Int, petId: Int)
}