package com.renansilva235.controlador_habitos.viewModel.user

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.renansilva235.controlador_habitos.data.remote.api.RegisterApi
import com.renansilva235.controlador_habitos.data.remote.dto.RegisterRequest
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import com.renansilva235.controlador_habitos.data.remote.dto.ErrorResponse
import com.google.gson.Gson
import android.util.Log
import com.renansilva235.controlador_habitos.data.remote.dto.RegisterResponse
import okhttp3.Response

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
    var isLoading by mutableStateOf(false)
    var registerSuccess by mutableStateOf<Boolean>(false)
    var registerError by mutableStateOf<String?>(null)
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

    fun registerUser(){
        if(!validateForm()) return

         viewModelScope.launch {
             isLoading = true
             registerError = null
             registerSuccess = false

            try {
                val response = RegisterApi.instance.register(
                    RegisterRequest(
                        name = name.trim(),
                        email = email.trim(),
                        password = password
                    )
                )

                var successResponse: RegisterResponse = RegisterResponse(
                    success = response.success,
                    codes = response.codes,
                    data = response.data
                )
                //if(!response.success) registerError = response.msg
                println(successResponse)
            }catch (error: HttpException){
                val errorBody = error.response()?.errorBody()?.string()
                val errorResponse = errorBody?.let{
                    Gson().fromJson(it, ErrorResponse::class.java)
                }

                //registerError = errorResponse?.message ?: getApplication<Application>().resources.getString(R.string.sample_string)

            }
             catch (error: IOException){
                 registerError = "Não foi possível conectar ao servidor."
             }
             finally {
                 isLoading = false
             }
         }

    }
}