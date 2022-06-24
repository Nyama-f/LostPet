package com.example.lostpet.di

import android.app.Application
import android.content.Context
import com.example.lostpet.ui.screens.MapFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, ViewModelModule::class, UseCasesModule::class])
interface ApplicationComponent {

    fun inject(fragment: MapFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): ApplicationComponent
    }
}