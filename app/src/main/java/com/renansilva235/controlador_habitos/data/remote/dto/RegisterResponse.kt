package com.renansilva235.controlador_habitos.data.remote.dto

data class RegisterResponse(
    val success: Boolean,
    val codes: String,
    val data: Any? = null
)