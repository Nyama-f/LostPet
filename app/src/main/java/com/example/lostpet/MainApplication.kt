package com.example.lostpet

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.lostpet.di.ApplicationComponent
import com.example.lostpet.di.DaggerApplicationComponent
import com.example.lostpet.utils.Consts.MAIN


class MainApplication: Application() {

//    private var _globalUserID: Int? = null
//    val globalUserID: Int
//        get() = checkNotNull(_globalUserID) { "globalUserId is null" }



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

    companion object{
        var globalCurrentUserId = 0
    }

}