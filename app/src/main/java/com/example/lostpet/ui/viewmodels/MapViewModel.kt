package com.example.lostpet.ui.viewmodels

import android.util.Log
import android.util.LogPrinter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lostpet.data.model.Pet
import com.example.lostpet.data.model.User
import com.example.lostpet.domain.useCases.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getPetsUseCase: GetPetsUseCase,
    private val getUserUseCase: GetUserUseCase
): ViewModel(){

    private val _pets = MutableStateFlow<List<Pet>>(listOf())
    val pets = _pets.asStateFlow()


    private val _users = MutableStateFlow<List<User>>(listOf())
    val users = _users.asStateFlow()

   // private val userT = mutableListOf<User>()


    private val _user = MutableStateFlow(User(
        "None",
        "",
        "",
        "",
        "",
        mutableListOf()
    ))
    val user = _user.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("Exception", throwable.toString())
    }


//    fun getX(){
//        viewModelScope.launch(exceptionHandler){
//            getUsersUseCase.invoke()
//                .filterNotNull()
//                .collect{
//                    userT.addAll(it)
//                }
//            for (user in userT){
//                getPetsUseCase.invoke(userId = user.userId?.toInt() ?: 1)
//                    .filterNotNull()
//                    .collect{
//                        _pets.emit(it)
//                    }
//            }
//        }
//    }

    fun getUsers(){
        viewModelScope.launch(exceptionHandler){
            getUsersUseCase.invoke()
                .collect{
                    _users.emit(it)
                }
        }
    }

    fun getPets(userId: Int){
        viewModelScope.launch(exceptionHandler){
            getPetsUseCase.invoke(userId)
                .collect{
                    _pets.emit(it)
                }
        }
    }



    fun getUser(userId: Int){
        viewModelScope.launch(exceptionHandler){
            getUserUseCase.invoke(userId)
                .collect{
                    _user.emit(it)
                }
        }
    }

    fun getUsersAndPets() {
        viewModelScope.launch(exceptionHandler) {
            getUsersUseCase.invoke()
                .collect {
                    _users.emit(it)
                }
            for (user in _users.value) {
                getPetsUseCase.invoke(userId = user.userId?.toInt() ?: 1)
                    .collect {
                        _pets.emit(it)
                    }
            }
        }
    }


}

