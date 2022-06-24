package com.example.lostpet.di

import android.content.Context
import com.example.lostpet.ui.screens.MapFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class, UseCasesModule::class, RepositoryModule::class])
interface ApplicationComponent {

    fun inject(fragment: MapFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): ApplicationComponent
    }
}