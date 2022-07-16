package com.example.lostpet.domain.useCases.impl

import android.util.Log
import com.example.lostpet.data.model.User
import com.example.lostpet.domain.repository.ApiRepository
import com.example.lostpet.domain.useCases.AddUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddUserUseCaseImpl @Inject constructor(
    private val apiRepository: ApiRepository
): AddUserUseCase {

    override suspend fun invoke(user: User) {
        withContext(Dispatchers.IO){
            apiRepository.addUser(user = user)
        }
    }
}