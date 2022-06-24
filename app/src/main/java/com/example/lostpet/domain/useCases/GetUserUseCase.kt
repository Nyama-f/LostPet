package com.example.lostpet.domain.useCases

import com.example.lostpet.data.model.User
import kotlinx.coroutines.flow.Flow

interface GetUserUseCase {

    operator fun invoke(userId: Int): Flow<User>

}