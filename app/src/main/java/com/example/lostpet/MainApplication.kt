package com.example.lostpet

import android.app.Application
import com.example.lostpet.di.ApplicationComponent
import com.example.lostpet.di.DaggerApplicationComponent

class MainApplication: Application() {
    private var _appComponent: ApplicationComponent? = null
    val appComponent: ApplicationComponent
    get() = requireNotNull(_appComponent) {
        "Application component wasn't initialized"
    }

    override fun onCreate() {
        super.onCreate()

        _appComponent = DaggerApplicationComponent.builder()
            .appContext(this)
            .build()
    }
}