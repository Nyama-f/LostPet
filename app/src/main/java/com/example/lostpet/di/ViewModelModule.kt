package com.example.lostpet.di

import androidx.lifecycle.ViewModel
import com.example.lostpet.ui.viewmodels.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    fun bindMapViewModel(viewModel: MapViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    fun bindRegistrationViewModel(viewModel: RegistrationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddMarkViewModel::class)
    fun bindAddmarkViewModel(viewModel: AddMarkViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    fun bindAccountViewModel(viewModel: AccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListOfMarksViewModel::class)
    fun bindListOfMarksViewModel(viewModel: ListOfMarksViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditUserViewModel::class)
    fun bindEditUserViewModel(viewModel: EditUserViewModel): ViewModel

//    @Binds
//    fun viewModelFactory(viewModel: MapViewModel): ViewModelProvider.Factory


}