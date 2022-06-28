package com.example.lostpet.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lostpet.data.model.User
import com.example.lostpet.domain.useCases.AddUserUseCase
import com.example.lostpet.domain.useCases.GetUsersUseCase
import com.example.lostpet.utils.Consts.MAIN
import com.example.lostpet.utils.PersistantStorage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase,
    private val getUsersUseCase: GetUsersUseCase
): ViewModel() {

    private val sharedPreferences: PersistantStorage = PersistantStorage()

    private val _users = MutableStateFlow<List<User>>(listOf())
    val users = _users.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("Exception", throwable.toString())
    }

    fun addUser(user: User){
        with(sharedPreferences) {
            initContext(cntx = MAIN)
            addProperty(name = "userLogin", value = user.userLogin)
            Log.d("UserNyama1","${getProperty("userLogin")}")
        }
        viewModelScope.launch(exceptionHandler){
            addUserUseCase(user)
            Log.d("UserNyama1","${user}")
        }
    }

    fun getUsers(){
        viewModelScope.launch(exceptionHandler) {
            getUsersUseCase.invoke()
                .filterNotNull()
                .collect{
                    Log.d("UserNyama1", "$it")
                    _users.emit(it)
                }
        }
    }
}