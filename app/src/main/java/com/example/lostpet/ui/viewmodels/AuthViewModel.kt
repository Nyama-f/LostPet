package com.example.lostpet.ui.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lostpet.data.model.User
import com.example.lostpet.domain.useCases.GetUsersUseCase
import com.example.lostpet.utils.Consts
import com.example.lostpet.utils.Consts.MAIN
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

class AuthViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(listOf())
    val users = _users.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("Exception", throwable.toString())
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
    fun checkUsers(login: String, password: String): Boolean{
        if(login.trim().isEmpty() && password.trim().isEmpty()){
            Toast.makeText(MAIN, "Не все поля заполнены", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        for(user in _users.value){
            Log.d("Nyama", "UserLogin${user.userLogin} UserPassword${user.userPassword}")
            if(user.userLogin == login && user.userPassword == password){
                MAIN.prefs.edit().putInt("currentUserId", user.userId?.toInt() ?: 0).commit()
                Log.d("Nyama", "ID entered user : " +
                        "${MAIN.prefs.getInt("currentUserId", 0)}")
                return true
            }
        }
        Toast.makeText(MAIN, "Пользователь не зарегистрирован", Toast.LENGTH_SHORT)
            .show()
        return false
    }
}
