package com.example.lostpet.ui.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lostpet.R
import com.example.lostpet.data.model.Pet
import com.example.lostpet.databinding.FragmentAddMarkBinding
import com.example.lostpet.ui.viewmodels.AddMarkViewModel
import com.example.lostpet.ui.viewmodels.ViewModelFactory
import com.example.lostpet.utils.appComponent
import javax.inject.Inject

class AddMarkFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<AddMarkViewModel> { viewModelFactory }

    private var _binding: FragmentAddMarkBinding? = null
    private val binding get() = checkNotNull(_binding) { "Binding is null" }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddMarkBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            val pet = Pet(
                petAvatar = "123",
                petColor = binding.innerEditTextColor.text.toString(),
                petDescription = binding.editTextDescriptionOfPet.text.toString(),
                petLatitude = arguments?.getString("latitude")?: "",
                petLongitude = arguments?.getString("longitude")?: "",
                petType = binding.innerEditTextTypePet.text.toString(),
                petUserId = 1
            )
            viewModel.addPet(pet = pet, 1)
            // var users = viewModel.getUsers()
            // Log.d("UserNyama1", "$users")
            //TODO Сделать добавление аватара(можно по названию файла)
            findNavController().navigate(R.id.action_addMarkFragment_to_mapFragment)
        }
    }
}