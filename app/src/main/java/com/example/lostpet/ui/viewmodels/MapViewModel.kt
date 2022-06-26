package com.example.lostpet.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lostpet.data.model.Pet
import com.example.lostpet.domain.useCases.AddPetUseCase
import com.example.lostpet.domain.useCases.AddUserUseCase
import com.example.lostpet.domain.useCases.GetPetUseCase
import com.example.lostpet.domain.useCases.GetPetsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase,
    private val addPetUseCase: AddPetUseCase,
    private val getPetsUseCase: GetPetsUseCase
): ViewModel(){

    private val _pets = MutableStateFlow<List<Pet>>(listOf())
    val pets = _pets.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("Exception", throwable.toString())
    }

    fun addMark(pet: Pet){
        viewModelScope.launch(exceptionHandler){
            addPetUseCase(pet)
        }
    }

    fun getPets(){
        viewModelScope.launch(exceptionHandler) {
            getPetsUseCase.invoke()
                .filterNotNull()
                .collect{
                    _pets.emit(it)
                }
        }
    }

}

/*
TODO:Сразу после регистарции делать
Нужно сделать корректное добавление маркеров
Добаввить isChecked, и сделать через ViewModel !!!
 */