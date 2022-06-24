package com.example.lostpet.domain.useCases

import com.example.lostpet.data.model.User

interface AddUserUseCase {
    operator fun invoke(user: User)
}