package com.example.lostpet.domain.useCases.impl

import com.example.lostpet.data.model.User
import com.example.lostpet.domain.repository.ApiRepository
import com.example.lostpet.domain.useCases.GetUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUsersUseCaseImpl @Inject constructor(
    private val apiRepository: ApiRepository
): GetUsersUseCase {

    override fun invoke(): Flow<List<User>> {
        return apiRepository.getUsers().flowOn(Dispatchers.IO)
    }

}