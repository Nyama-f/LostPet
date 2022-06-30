package com.example.lostpet.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lostpet.data.model.Pet
import com.example.lostpet.data.model.User
import com.example.lostpet.domain.useCases.GetPetsUseCase
import com.example.lostpet.domain.useCases.GetUsersUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListOfMarksViewModel @Inject constructor(
    private val getPetsUseCase: GetPetsUseCase,
    private val getUsersUseCase: GetUsersUseCase
): ViewModel() {

    private val _pets = MutableStateFlow<List<Pet>>(listOf())
    val pets = _pets.asStateFlow()

    private val _users = MutableStateFlow<List<User>>(listOf())
    val users = _users.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("Exception", throwable.toString())
    }

    fun getPets(){
        viewModelScope.launch(exceptionHandler) {
            for (user in _users.value){
                getPetsUseCase.invoke(userId = user.userId?.toInt() ?: 1)
                    .filterNotNull()
                    .collect{
                        _pets.emit(it)
                    }
            }
        }
    }

    fun getUsers(){
        viewModelScope.launch(exceptionHandler) {
            getUsersUseCase.invoke()
                .filterNotNull()
                .collect{
                    _users.emit(it)
                }
        }
    }
}