package com.example.lostpet.di

import android.content.Context
import com.example.lostpet.ui.screens.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class, UseCasesModule::class, RepositoryModule::class])
interface ApplicationComponent {

    fun inject(fragment: MapFragment)

    fun inject(fragment: RegistrationFragment)

    fun inject(fragment: AddMarkFragment)

    fun inject(fragment: AccountFragment)

    fun inject(fragment: ListOfMarksFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): ApplicationComponent
    }
}