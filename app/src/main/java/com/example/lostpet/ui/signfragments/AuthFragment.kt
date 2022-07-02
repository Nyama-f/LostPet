package com.example.lostpet.ui.signfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lostpet.MainApplication
import com.example.lostpet.R
import com.example.lostpet.databinding.FragmentAuthBinding
import com.example.lostpet.databinding.FragmentLoginOrAuthBinding
import com.example.lostpet.utils.activityNavController
import com.example.lostpet.utils.navigateSafely

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = checkNotNull(_binding) { "Binding is null" }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEnter.setOnClickListener{
            findNavController().navigate(R.id.action_authFragment_to_mainFlowFragment)
        }
    }

    private fun clickSignIn() {
        binding.btnEnter.setOnClickListener {
            if(MainApplication.globalCurrentUserId != 0){
                activityNavController().navigateSafely(R.id.action_authFragment_to_mainFlowFragment)
            }
        }
    }

    private fun clickSignUp() {
        binding.btnEnter.setOnClickListener {
            findNavController().navigateSafely(R.id.action_loginOrAuthFragment_to_registrationFragment)
        }
    }

}