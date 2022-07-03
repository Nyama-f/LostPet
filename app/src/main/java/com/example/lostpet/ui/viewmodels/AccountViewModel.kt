package com.example.lostpet.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lostpet.data.model.Pet
import com.example.lostpet.data.model.User
import com.example.lostpet.domain.useCases.*
import com.example.lostpet.utils.Consts
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val getPetsUseCase: GetPetsUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val editUserUseCase: EditUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val deletePetUseCase: DeletePetUseCase
) : ViewModel() {

    private val _pets = MutableStateFlow<List<Pet>>(listOf())
    val pets = _pets.asStateFlow()

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

    fun getPets() {
        viewModelScope.launch(exceptionHandler) {
            getPetsUseCase.invoke(userId = Consts.MAIN.prefs.getInt("currentUserId", 0))
                .filterNotNull()
                .collect {
                    _pets.emit(it)
                }
        }
    }
    fun getUser() {
        viewModelScope.launch(exceptionHandler) {
            getUserUseCase.invoke(userId = Consts.MAIN.prefs.getInt("currentUserId", 0))
                .filterNotNull()
                .collect {
                    _user.emit(it)
                }
        }
    }

    fun deletePet(petId: Int){
        viewModelScope.launch(exceptionHandler){
            Log.d("DelUser", "В AccountViewModel UserId ${Consts.MAIN.prefs.getInt("currentUserId", 0)} petId ${petId}")
            deletePetUseCase.invoke(Consts.MAIN.prefs.getInt("currentUserId", 0), petId = petId)

        }
    }

    fun editUser(){
        viewModelScope.launch(exceptionHandler){
            editUserUseCase.invoke(userId = Consts.MAIN.prefs.getInt("currentUserId", 0))
                .collect{
                    _user.emit(it)
                }
        }
    }

    fun deleteUser(){
        viewModelScope.launch(exceptionHandler){
            deleteUserUseCase.invoke(userId = Consts.MAIN.prefs.getInt("currentUserId", 0))
            Consts.MAIN.prefs.edit().putInt("currentUserId", 0).commit()
        }
    }

    fun outOfAccount(){
        Consts.MAIN.prefs.edit().putInt("currentUserId", 0).commit()
    }

}