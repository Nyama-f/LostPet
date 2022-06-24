package com.example.lostpet.utils

import android.content.Context
import com.example.lostpet.MainApplication
import com.example.lostpet.di.ApplicationComponent

val Context.appComponent: ApplicationComponent
    get() = if (this is MainApplication) {
        this.appComponent
    } else {
        this.applicationContext.appComponent
    }