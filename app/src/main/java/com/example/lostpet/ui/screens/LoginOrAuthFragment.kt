package com.example.lostpet.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lostpet.R
import com.example.lostpet.databinding.FragmentLoginOrAuthBinding
import com.example.lostpet.databinding.FragmentRegistrationBinding


class LoginOrAuthFragment : Fragment() {


    private var _binding: FragmentLoginOrAuthBinding? = null
    private val binding get() = checkNotNull(_binding) { "Binding is null" }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginOrAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAuth.setOnClickListener {
            findNavController().navigate(R.id.action_loginOrAuthFragment_to_authFragment)
        }

        binding.btnRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_loginOrAuthFragment_to_registrationFragment)
        }
    }


}