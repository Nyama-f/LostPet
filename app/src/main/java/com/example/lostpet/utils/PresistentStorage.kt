package com.example.lostpet.utils

import android.content.Context
import android.content.SharedPreferences


class PersistentStorage {

    private var context: Context? = null


    fun initContext(cntx: Context) {
        context = cntx
    }

    private fun init() {
        settings = context?.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
        editor = settings?.edit()
    }

    fun addProperty(name: String?, value: String?) {
        if (settings == null) {
            init()
        }
        editor!!.putString(name, value)
        editor!!.apply()
    }

    fun  getProperty(name: String): String? {
        if(settings == null){
            init()
        }
        return settings?.getString(name, null);
    }

    companion object{

        private val STORAGE_NAME = "systemStorage"
        private var settings: SharedPreferences? = null
        private var editor: SharedPreferences.Editor? = null
    }
}