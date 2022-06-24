package com.example.lostpet.domain.useCases.impl

import com.example.lostpet.data.model.User
import com.example.lostpet.domain.repository.ApiRepository
import com.example.lostpet.domain.useCases.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserUseCaseImpl @Inject constructor(
    private val apiRepository: ApiRepository
): GetUserUseCase {
    override fun invoke(userId: Int): Flow<User> {
        return apiRepository.getUser(userId = userId).flowOn(Dispatchers.IO)
    }
}