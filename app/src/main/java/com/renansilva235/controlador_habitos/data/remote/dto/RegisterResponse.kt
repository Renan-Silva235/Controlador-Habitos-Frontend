package com.renansilva235.controlador_habitos.data.remote.dto

import com.renansilva235.controlador_habitos.data.remote.enums.CodesResponse

data class RegisterResponse(
    val success: Boolean,
    val codes: CodesResponse,
    val data: Any? = null
)