package com.example.lostpet.ui.signfragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lostpet.R
import com.example.lostpet.data.model.User
import com.example.lostpet.databinding.FragmentRegistrationBinding
import com.example.lostpet.ui.viewmodels.RegistrationViewModel
import com.example.lostpet.ui.viewmodels.ViewModelFactory
import com.example.lostpet.utils.TextValidator
import com.example.lostpet.utils.appComponent
import javax.inject.Inject


class RegistrationFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<RegistrationViewModel> { viewModelFactory }

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = checkNotNull(_binding) { "Binding is null" }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegistration.setOnClickListener { v -> v.isClickable = false }
        checkLoginIsNull()
        checkNameIsNull()
        checkPhoneIsNull()
        checkPasswordsEditTextInputEachOther()
        binding.btnRegistration.setOnClickListener {
            val user = User(
                userName = binding.innerEditTextName.text.toString(),
                userPhone = binding.innerEditTextPhone.text.toString(),
                userLogin = binding.innerEditTextLogin.text.toString(),
                userPassword = binding.innerEditTextPassword.text.toString(),
                userId = null,
                listOfMarks = mutableListOf()
            )
            viewModel.addUser(user = user)

            if(binding.innerEditTextLogin.text != null &&
                binding.innerEditTextPhone.text != null &&
                binding.innerEditTextPassword.text != null &&
                binding.innerEditTextPasswordRepeat.text != null &&
                binding.innerEditTextName.text != null){
                findNavController().navigate(R.id.action_registrationFragment_to_authFragment)
            }
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
                    binding.btnRegistration.isEnabled = false
                }
                if (innerPassword.text.toString() == innerPasswordRepeat.text.toString()) {
                    passwordRepeat.error = null
                    passwordRepeat.isErrorEnabled = false
                    binding.btnRegistration.isEnabled = true
                }
            }

        })
    }

    private fun checkNameIsNull(){
        val innerName = binding.innerEditTextName
        val nameField = binding.editTextName
        innerName.addTextChangedListener(object : TextValidator(innerName){
            override fun validate(textView: TextView?, text: String?) {
                if(innerName.text.toString().trim().isEmpty()) {
                    nameField.error = getString(R.string.fieldIsNull)
                    nameField.isErrorEnabled = true
                    binding.btnRegistration.isEnabled = false
                }else {
                    nameField.error = null
                    nameField.isErrorEnabled = false
                    binding.btnRegistration.isEnabled = true
                }
            }
        })
    }

    private fun checkLoginIsNull(){
        val innerLogin = binding.innerEditTextLogin
        val loginField = binding.editTextLogin
        innerLogin.addTextChangedListener(object : TextValidator(innerLogin){
            override fun validate(textView: TextView?, text: String?) {
                if(innerLogin.text.toString().trim().isEmpty()) {
                    loginField.error = getString(R.string.fieldIsNull)
                    loginField.isErrorEnabled = true
                    binding.btnRegistration.isEnabled = false
                }else {
                    loginField.error = null
                    loginField.isErrorEnabled = false
                    binding.btnRegistration.isEnabled = true
                }
            }

        })
    }

    private fun checkPhoneIsNull(){
        val innerPhone = binding.innerEditTextPhone
        val phoneField = binding.editTextPhone
        val r1 = Regex("^(8|\\+7)(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{10,11}\$")
        innerPhone.addTextChangedListener(object : TextValidator(innerPhone){
            override fun validate(textView: TextView?, text: String?) {
                if(r1.matches(innerPhone.text.toString())){
                    phoneField.error = null
                    phoneField.isErrorEnabled = false
                    binding.btnRegistration.isEnabled = true
                }else{
                    phoneField.error = getString(R.string.phoneIsIncorrect)
                    phoneField.isErrorEnabled = true
                    binding.btnRegistration.isEnabled = false
                }
            }
        })
    }
}

