package com.example.lostpet.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lostpet.data.model.Pet
import com.example.lostpet.utils.PersistentStorage
import com.example.lostpet.domain.useCases.GetPetsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val getPetsUseCase: GetPetsUseCase
) : ViewModel() {

    private val sharedPreferences: PersistentStorage = PersistentStorage()

    private val _pets = MutableStateFlow<List<Pet>>(listOf())
    val pets = _pets.asStateFlow()


    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("Exception", throwable.toString())
    }

    fun getPets() {
        viewModelScope.launch(exceptionHandler) {
            getPetsUseCase.invoke(userId = 1)
                .filterNotNull()
                .collect {
                    _pets.emit(it)
                }
        }
    }

}