package com.example.lostpet.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lostpet.data.model.EditData
import com.example.lostpet.data.model.User
import com.example.lostpet.domain.useCases.EditUserUseCase
import com.example.lostpet.utils.Consts
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditUserViewModel @Inject constructor(
    private val editUserUseCase: EditUserUseCase
): ViewModel() {

    private val _user = MutableStateFlow(
        User(
        "None",
        "",
        "",
        "",
        "",
        mutableListOf()
    )
    )
    val user = _user.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("Exception", throwable.toString())
    }

    fun editUser(login: String, name: String, mobileNumber: String){
        val editData = EditData(name = name, login = login, mobilePhone = mobileNumber)
        viewModelScope.launch(exceptionHandler){
            editUserUseCase.invoke(userId = Consts.MAIN.prefs.getInt("currentUserId", 0), editData)
                .collect{
                    _user.emit(it)
                }
        }
    }
}