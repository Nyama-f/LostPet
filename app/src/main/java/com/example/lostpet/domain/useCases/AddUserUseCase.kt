package com.example.lostpet.domain.useCases

import com.example.lostpet.data.model.User

interface AddUserUseCase {
    suspend operator fun invoke(user: User)
}