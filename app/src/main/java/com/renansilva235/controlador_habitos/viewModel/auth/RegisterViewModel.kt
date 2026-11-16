package com.renansilva235.controlador_habitos.viewModel.auth

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class RegisterViewModel : ViewModel() {
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordConfirm by mutableStateOf("")
    var termsAccepted by mutableStateOf(false)

    var nameError by mutableStateOf(false)
    var emailError by mutableStateOf(false)
    var passwordError by mutableStateOf(false)
    var passwordConfirmError by mutableStateOf(false)
    var termsAcceptedError by mutableStateOf(false)

    var nameRequiredError by mutableStateOf(false)
    var emailRequiredError by mutableStateOf(false)
    var passwordRequiredError by mutableStateOf(false)
    var passwordConfirmRequiredError by mutableStateOf(false)
    fun isNameValid() : Boolean {
        return name.isNotBlank() &&
                name.trim().length in 3..100 &&
                name.matches(Regex("[\\p{L} ]+"))
    }

    fun isEmailValid() : Boolean {
        return email.isNotBlank() &&
            Patterns.EMAIL_ADDRESS
            .matcher(email)
            .matches()
    }

    fun isPasswordValid() : Boolean {
        return password.isNotBlank() && password.length >= 6
    }

    fun isPasswordConfirmationValid() : Boolean {
        return passwordConfirm.isNotBlank() && password == passwordConfirm
    }

    fun areTermsAccepted(): Boolean {
        return termsAccepted
    }

    fun validateForm() : Boolean {
        nameError = !isNameValid()
        emailError = !isEmailValid()
        passwordError = !isPasswordValid()
        passwordConfirmError = !isPasswordConfirmationValid()
        termsAcceptedError = !areTermsAccepted()

        nameRequiredError = name.isBlank()
        emailRequiredError = email.isBlank()
        passwordRequiredError = password.isBlank()
        passwordConfirmRequiredError = passwordConfirm.isBlank()

        if(nameError) name = ""
        if(emailError) email = ""
        if(passwordError) password = ""
        if(passwordConfirmError) passwordConfirm = ""

        return !nameError &&
                !emailError &&
                !passwordError &&
                !passwordConfirmError &&
                !termsAcceptedError
    }
}