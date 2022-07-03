package com.example.lostpet.domain.useCases.impl

import com.example.lostpet.data.model.User
import com.example.lostpet.domain.repository.ApiRepository
import com.example.lostpet.domain.useCases.DeleteUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteUserUseCaseImpl @Inject constructor(
    private val apiRepository: ApiRepository
): DeleteUserUseCase {

    override suspend fun invoke(userId: Int) {
        withContext(Dispatchers.IO){
            apiRepository.deleteUser(userId = userId)
        }
    }
}