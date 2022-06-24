package com.example.lostpet.domain.useCases.impl

import com.example.lostpet.data.model.Pet
import com.example.lostpet.domain.repository.ApiRepository
import com.example.lostpet.domain.useCases.GetPetsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPetsUseCaseImpl @Inject constructor(
    private val apiRepository: ApiRepository
): GetPetsUseCase {
    override fun invoke(): Flow<List<Pet>> {
        return apiRepository.getPets().flowOn(Dispatchers.IO)
    }
}