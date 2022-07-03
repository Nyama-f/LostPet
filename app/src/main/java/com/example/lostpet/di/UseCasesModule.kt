package com.example.lostpet.di

import com.example.lostpet.domain.useCases.*
import com.example.lostpet.domain.useCases.impl.*
import dagger.Binds
import dagger.Module

@Module
interface UseCasesModule {

    @Binds
    fun bindAddPetUseCase(impl: AddPetUseCaseImpl): AddPetUseCase

    @Binds
    fun bindGetPetUseCase(impl: GetPetUseCaseImpl): GetPetUseCase

    @Binds
    fun bindGetPetsUseCase(impl: GetPetsUseCaseImpl): GetPetsUseCase

    @Binds
    fun bindEditPetUseCase(impl: DeletePetUseCaseImpl): DeletePetUseCase

    @Binds
    fun bindAddUserUseCase(impl: AddUserUseCaseImpl): AddUserUseCase

    @Binds
    fun bindGetUserUseCase(impl: GetUserUseCaseImpl): GetUserUseCase

    @Binds
    fun bindGetUsersUseCase(impl: GetUsersUseCaseImpl): GetUsersUseCase

    @Binds
    fun bindDeleteUserUseCase(impl: DeleteUserUseCaseImpl): DeleteUserUseCase

    @Binds
    fun bindEditUserUseCase(impl: EditUserUseCaseImpl): EditUserUseCase

}