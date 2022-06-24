package com.example.lostpet.domain.useCases

import com.example.lostpet.data.model.Pet

interface AddPetUseCase {

    operator fun invoke(pet: Pet)

}