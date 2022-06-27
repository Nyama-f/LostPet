package com.example.lostpet.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lostpet.R
import com.example.lostpet.data.model.User
import com.example.lostpet.databinding.FragmentRegistrationBinding
import com.example.lostpet.ui.viewmodels.MapViewModel
import com.example.lostpet.ui.viewmodels.RegistrationViewModel
import com.example.lostpet.ui.viewmodels.ViewModelFactory
import com.example.lostpet.utils.TextValidator
import javax.inject.Inject
import kotlin.math.log


class RegistrationFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<RegistrationViewModel> { viewModelFactory }

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = checkNotNull(_binding) { "Binding is null" }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkLoginAndNameIsNull()
        checkPasswordsEditTextInputEachOther()
        binding.btnRegistration.setOnClickListener {
            val user = User(
                userName = binding.editTextName.toString(),
                userAvatar = "123",
                userLogin = binding.editTextLogin.toString(),
                userPassword = binding.editTextPassword.toString(),
                listOfMarks = listOf())


            findNavController().navigate(R.id.action_registrationFragment_to_mapFragment)
        }
    }

    private fun checkPasswordsEditTextInputEachOther(){
        val innerPassword = binding.innerEditTextPassword
        val passwordRepeat = binding.editTextPasswordRepeat
        val innerPasswordRepeat = binding.innerEditTextPasswordRepeat
        innerPasswordRepeat.addTextChangedListener(object : TextValidator(innerPasswordRepeat){
            override fun validate(textView: TextView?, text: String?) {
                if(innerPassword.text.toString() != innerPasswordRepeat.text.toString()){
                    passwordRepeat.error = getString(R.string.notEquallyPasswordFields)
                    passwordRepeat.isErrorEnabled = true
                }
                if (innerPassword.text.toString() == innerPasswordRepeat.text.toString()) {
                    passwordRepeat.error = null
                    passwordRepeat.isErrorEnabled = false
                }
            }

        })
    }

    private fun checkLoginAndNameIsNull(){
        val innerName = binding.innerEditTextName
        val nameField = binding.editTextName
        val innerLogin = binding.innerEditTextLogin
        val loginField = binding.editTextLogin
        innerName.addTextChangedListener(object : TextValidator(innerName){
            override fun validate(textView: TextView?, text: String?) {
                if(innerName.text.toString().trim().length == 0) {
                    nameField.error = getString(R.string.fieldIsNull)
                    nameField.isErrorEnabled = true
                }else {
                    nameField.error = null
                    nameField.isErrorEnabled = false
                }
            }
        })
        innerLogin.addTextChangedListener(object : TextValidator(innerLogin){
            override fun validate(textView: TextView?, text: String?) {
                if(innerLogin.text.toString().trim().length == 0) {
                    loginField.error = getString(R.string.fieldIsNull)
                    loginField.isErrorEnabled = true
                }else {
                    loginField.error = null
                    loginField.isErrorEnabled = false
                }
            }

        })
    }
}

