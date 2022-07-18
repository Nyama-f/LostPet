package com.example.lostpet.domain.useCases.impl

import com.example.lostpet.data.model.EditData
import com.example.lostpet.data.model.User
import com.example.lostpet.domain.repository.ApiRepository
import com.example.lostpet.domain.useCases.EditUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EditUserUseCaseImpl @Inject constructor(
    private val apiRepository: ApiRepository
): EditUserUseCase {


    override fun invoke(userId: Int, editData: EditData): Flow<User> {
        return apiRepository.editUser(userId = userId, editData = editData).flowOn(Dispatchers.IO)
    }
}