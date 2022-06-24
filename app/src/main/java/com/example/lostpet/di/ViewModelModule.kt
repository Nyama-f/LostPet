package com.example.lostpet.di

import androidx.lifecycle.ViewModel
import com.example.lostpet.ui.viewmodels.MapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    fun bindMapViewModel(viewModel: MapViewModel): ViewModel




}