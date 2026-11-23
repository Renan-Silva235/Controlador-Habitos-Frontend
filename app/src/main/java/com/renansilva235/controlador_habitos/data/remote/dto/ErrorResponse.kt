package com.renansilva235.controlador_habitos.data.remote.dto

import com.renansilva235.controlador_habitos.data.remote.enums.CodesResponse

data class ErrorResponse(
    val status: Int,
    val codes: CodesResponse
)
