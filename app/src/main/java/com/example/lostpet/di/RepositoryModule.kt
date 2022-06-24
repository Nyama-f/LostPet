package com.example.lostpet.di

import com.example.lostpet.domain.repository.ApiRepository
import com.example.lostpet.domain.repository.impl.ApiRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindApiRepository(impl: ApiRepositoryImpl): ApiRepository
}