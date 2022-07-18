package com.example.lostpet.ui.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lostpet.R
import com.example.lostpet.databinding.FragmentAddMarkBinding
import com.example.lostpet.databinding.FragmentEditUserBinding
import com.example.lostpet.ui.viewmodels.AddMarkViewModel
import com.example.lostpet.ui.viewmodels.EditUserViewModel
import com.example.lostpet.ui.viewmodels.ViewModelFactory
import com.example.lostpet.utils.TextValidator
import com.example.lostpet.utils.activityNavController
import com.example.lostpet.utils.appComponent
import com.example.lostpet.utils.navigateSafely
import javax.inject.Inject
import kotlin.math.log


class EditUserFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<EditUserViewModel> { viewModelFactory }

    private var _binding: FragmentEditUserBinding? = null
    private val binding get() = checkNotNull(_binding) { "Binding is null" }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkLoginIsNull()
        checkNameIsNull()
        checkPhoneIsNull()
        binding.btnEditSave.setOnClickListener {
            val name = binding.innerEditTextName.text.toString()
            val number = binding.innerEditTextNumber.text.toString()
            val login = binding.innerEditTextLogin.text.toString()
            viewModel.editUser(login = login, name = name, mobileNumber = number)
            findNavController().navigate(R.id.action_editUserFragment_to_accountFragment)

        }
    }

    private fun checkNameIsNull(){
        val innerName = binding.innerEditTextName
        val nameField = binding.editTextName
        innerName.addTextChangedListener(object : TextValidator(innerName){
            override fun validate(textView: TextView?, text: String?) {
                if(innerName.text.toString().trim().isEmpty()) {
                    nameField.error = getString(R.string.fieldIsNull)
                    nameField.isErrorEnabled = true
                    binding.btnEditSave.isEnabled = false
                }else {
                    nameField.error = null
                    nameField.isErrorEnabled = false
                    binding.btnEditSave.isEnabled = true
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
                    binding.btnEditSave.isEnabled = false
                }else {
                    loginField.error = null
                    loginField.isErrorEnabled = false
                    binding.btnEditSave.isEnabled = true
                }
            }

        })
    }

    private fun checkPhoneIsNull(){
        val innerPhone = binding.innerEditTextNumber
        val phoneField = binding.editTextNumber
        val r1 = Regex("^(8|\\+7)(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{10,11}\$")
        innerPhone.addTextChangedListener(object : TextValidator(innerPhone){
            override fun validate(textView: TextView?, text: String?) {
                if(r1.matches(innerPhone.text.toString())){
                    phoneField.error = null
                    phoneField.isErrorEnabled = false
                    binding.btnEditSave.isEnabled = true
                }else{
                    phoneField.error = getString(R.string.phoneIsIncorrect)
                    phoneField.isErrorEnabled = true
                    binding.btnEditSave.isEnabled = false
                }
            }
        })
    }

}