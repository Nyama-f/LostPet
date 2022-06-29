package com.example.lostpet.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lostpet.data.model.Pet
import com.example.lostpet.domain.useCases.AddPetUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddMarkViewModel @Inject constructor(
    private val addPetUseCase: AddPetUseCase
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("Exception", throwable.toString())
    }

    fun addPet(pet: Pet, userId: Int){
        viewModelScope.launch(exceptionHandler) {
            addPetUseCase(pet, userId = userId)
            Log.d("UserNyama1", "AddMarkViewModel")
        }
    }
}