package com.example.lostpet.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lostpet.domain.useCases.AddPetUseCase
import com.example.lostpet.domain.useCases.AddUserUseCase
import com.example.lostpet.domain.useCases.GetPetUseCase
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase,
    private val addPetUseCase: AddPetUseCase
): ViewModel(){

    fun addMark(){
        TODO("Установка метки")
    }


}