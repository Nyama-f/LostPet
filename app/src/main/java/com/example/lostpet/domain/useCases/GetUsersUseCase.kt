package com.example.lostpet.domain.useCases

import com.example.lostpet.data.model.User
import kotlinx.coroutines.flow.Flow

interface GetUsersUseCase {

    operator fun invoke(): Flow<List<User>>
}