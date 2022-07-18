package com.example.lostpet.utils

import android.widget.TextView
import com.example.lostpet.R

class Validation {
    //TODO Сделать общий валидатор для строк
//    private fun checkNameIsNull(innerEditTextName: ){
//        val innerName = binding.innerEditTextName
//        val nameField = binding.editTextName
//        innerName.addTextChangedListener(object : TextValidator(innerName){
//            override fun validate(textView: TextView?, text: String?) {
//                if(innerName.text.toString().trim().isEmpty()) {
//                    nameField.error = getString(R.string.fieldIsNull)
//                    nameField.isErrorEnabled = true
//                    binding.btnRegistration.isEnabled = false
//                }else {
//                    nameField.error = null
//                    nameField.isErrorEnabled = false
//                    binding.btnRegistration.isEnabled = true
//                }
//            }
//        })
//    }
//
//    private fun checkLoginIsNull(){
//        val innerLogin = binding.innerEditTextLogin
//        val loginField = binding.editTextLogin
//        innerLogin.addTextChangedListener(object : TextValidator(innerLogin){
//            override fun validate(textView: TextView?, text: String?) {
//                if(innerLogin.text.toString().trim().isEmpty()) {
//                    loginField.error = getString(R.string.fieldIsNull)
//                    loginField.isErrorEnabled = true
//                    binding.btnRegistration.isEnabled = false
//                }else {
//                    loginField.error = null
//                    loginField.isErrorEnabled = false
//                    binding.btnRegistration.isEnabled = true
//                }
//            }
//
//        })
//    }
//
//    private fun checkPhoneIsNull(){
//        val innerPhone = binding.innerEditTextPhone
//        val phoneField = binding.editTextPhone
//        val r1 = Regex("^(8|\\+7)(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{10,11}\$")
//        innerPhone.addTextChangedListener(object : TextValidator(innerPhone){
//            override fun validate(textView: TextView?, text: String?) {
//                if(r1.matches(innerPhone.text.toString())){
//                    phoneField.error = null
//                    phoneField.isErrorEnabled = false
//                    binding.btnRegistration.isEnabled = true
//                }else{
//                    phoneField.error = getString(R.string.phoneIsIncorrect)
//                    phoneField.isErrorEnabled = true
//                    binding.btnRegistration.isEnabled = false
//                }
//            }
//        })
//    }
}