package com.example.lostpet.domain.useCases.impl

import com.example.lostpet.data.model.Pet
import com.example.lostpet.domain.repository.ApiRepository
import com.example.lostpet.domain.useCases.GetPetsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.internal.userAgent
import javax.inject.Inject

class GetPetsUseCaseImpl @Inject constructor(
    private val apiRepository: ApiRepository
): GetPetsUseCase {
    override fun invoke(userId: Int): Flow<List<Pet>> {
        return apiRepository.getPets(userId = userId).flowOn(Dispatchers.IO)
    }
}