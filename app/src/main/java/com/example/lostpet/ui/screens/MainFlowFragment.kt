package com.example.lostpet.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lostpet.R
import com.example.lostpet.databinding.ActivityMainBinding
import com.example.lostpet.databinding.FragmentMainFlowFragmentBinding
import com.example.lostpet.ui.signfragments.BaseFlowFragment

class MainFlowFragment : BaseFlowFragment(
    R.layout.fragment_main_flow_fragment, R.id.nav_host_fragment_main
) {

   // private val binding by viewBinding(FragmentMainFlowFragmentBinding::bind)

    private var _binding: FragmentMainFlowFragmentBinding? = null
    private val binding get() = checkNotNull(_binding) { "Binding is null" }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainFlowFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupNavigation(navController: NavController) {
        binding.bottomNavigation.setupWithNavController(navController)
    }


}