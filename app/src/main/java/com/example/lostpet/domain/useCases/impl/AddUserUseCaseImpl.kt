package com.example.lostpet.domain.useCases.impl

import com.example.lostpet.data.model.User
import com.example.lostpet.domain.repository.ApiRepository
import com.example.lostpet.domain.useCases.AddUserUseCase
import javax.inject.Inject

class AddUserUseCaseImpl @Inject constructor(
    private val apiRepository: ApiRepository
): AddUserUseCase {

    override fun invoke(user: User) {
        apiRepository.addUser(user = user)
    }
}