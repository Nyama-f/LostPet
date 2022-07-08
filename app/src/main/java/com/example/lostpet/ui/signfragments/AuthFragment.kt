package com.example.lostpet.ui.signfragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.lostpet.R
import com.example.lostpet.databinding.FragmentAuthBinding
import com.example.lostpet.ui.viewmodels.AuthViewModel
import com.example.lostpet.ui.viewmodels.ViewModelFactory
import com.example.lostpet.utils.TextValidator
import com.example.lostpet.utils.appComponent
import javax.inject.Inject

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = checkNotNull(_binding) { "Binding is null" }


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<AuthViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnEnter.isEnabled = false
        viewModel.getUsers()
        checkLoginIsNull()
        checkPasswordIsNull()
        binding.btnEnter.setOnClickListener{
            val innerLogin = binding.innerEditTextLogin.text.toString()
            val innerPassword = binding.innerEditTextPassword.text.toString()
            Log.d("Nyama", "${viewModel.users.value}")
            lifecycleScope.launchWhenResumed {
                viewModel.users.collect{
                    if(viewModel.checkUsers(innerLogin, innerPassword)){
                        findNavController().navigate(R.id.action_authFragment_to_mainFlowFragment)
                    }
                }
            }
        }
    }
    private fun checkPasswordIsNull(){
        val innerName = binding.innerEditTextPassword
        val nameField = binding.editTextPassword
        innerName.addTextChangedListener(object : TextValidator(innerName){
            override fun validate(textView: TextView?, text: String?) {
                if(innerName.text.toString().trim().length == 0) {
                    nameField.error = getString(R.string.fieldIsNull)
                    nameField.isErrorEnabled = true
                    binding.btnEnter.isEnabled = false
                }else {
                    nameField.error = null
                    nameField.isErrorEnabled = false
                    binding.btnEnter.isEnabled = true
                }
            }
        })
    }

    private fun checkLoginIsNull(){
        val innerLogin = binding.innerEditTextLogin
        val loginField = binding.editTextLogin
        innerLogin.addTextChangedListener(object : TextValidator(innerLogin){
            override fun validate(textView: TextView?, text: String?) {
                if(innerLogin.text.toString().trim().length == 0) {
                    loginField.error = getString(R.string.fieldIsNull)
                    loginField.isErrorEnabled = true
                    binding.btnEnter.isEnabled = false
                }else {
                    loginField.error = null
                    loginField.isErrorEnabled = false
                    binding.btnEnter.isEnabled = true
                }
            }

        })
    }

}