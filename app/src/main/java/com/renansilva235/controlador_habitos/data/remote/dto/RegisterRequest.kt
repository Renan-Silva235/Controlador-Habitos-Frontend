package com.renansilva235.controlador_habitos.data.remote.dto

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)