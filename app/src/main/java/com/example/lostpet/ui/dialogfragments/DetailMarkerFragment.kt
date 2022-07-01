package com.example.lostpet.ui.dialogfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lostpet.R
import com.example.lostpet.data.model.Pet
import com.example.lostpet.databinding.FragmentDetailMarkerBinding
import com.example.lostpet.databinding.FragmentMapsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class DetailMarkerFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDetailMarkerBinding? = null
    private val binding get() = checkNotNull(_binding) { "Binding is null" }

    private val pet: Pet? by lazy {
        arguments?.getParcelable<Pet>("pet")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailMarkerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            textTypePet.text = pet?.petType
            textColorPet.text = pet?.petColor
            textAuthorPet.text = pet?.petUserId.toString()
        }
    }

    /*
    Доделать BottomSheetFragment(сделать закругленные углы + добавить кнопки
    + Решить траблы аппы
     */



}