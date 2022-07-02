package com.example.lostpet

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lostpet.databinding.ActivityMainBinding
import com.example.lostpet.utils.Consts.MAIN

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = checkNotNull(_binding) { "Binding is null" }

    val prefs by lazy { this.getSharedPreferences("currentUserId", Context.MODE_PRIVATE) }

    private val navController by lazy {
        val navFragment =
            supportFragmentManager.findFragmentById(R.id.navContainer) as NavHostFragment
        navFragment.findNavController()
    }

//     val navController by lazy {
//        Navigation.findNavController(this, R.id.navContainer)
//    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MAIN = this
        if(prefs.getInt("currentUserId", -1) == -1) prefs.edit().putInt("currentUserId", 0).commit()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        setContentView(binding.root)
        Log.d("Shared", "${prefs.getInt("currentUserId", -1)}")
        when{
            MAIN.prefs.getInt("currentUserId", 0) == 0 -> {
                navGraph.setStartDestination(R.id.signFlowFragment)
            }
            MAIN.prefs.getInt("currentUserId", 0) != 0 -> {
                navGraph.setStartDestination(R.id.mainFlowFragment)
            }
        }
        navController.graph = navGraph

      //  binding.bottomNav.setupWithNavController(navController)
        // binding.bottomNav.itemIconTintList = null
    }


    override fun onStop() {
        super.onStop()

    }
}