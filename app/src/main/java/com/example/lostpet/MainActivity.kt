package com.example.lostpet

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lostpet.databinding.ActivityMainBinding
import com.example.lostpet.utils.Consts.MAIN

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = checkNotNull(_binding) { "Binding is null" }

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
        _binding = ActivityMainBinding.inflate(layoutInflater)
        var prefs = this.getSharedPreferences("currentUserId", Context.MODE_PRIVATE)
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        setContentView(binding.root)
        when{
            MainApplication.globalCurrentUserId == 0 -> {
                navGraph.setStartDestination(R.id.signFlowFragment)
            }
            MainApplication.globalCurrentUserId != 0 -> {
                navGraph.setStartDestination(R.id.mainFlowFragment)
            }
        }
        navController.graph = navGraph
        MAIN = this

      //  binding.bottomNav.setupWithNavController(navController)
        // binding.bottomNav.itemIconTintList = null
    }


    override fun onStop() {
        super.onStop()

    }
}