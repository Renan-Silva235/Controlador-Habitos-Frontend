package com.renansilva235.controlador_habitos.data.remote.api

import com.renansilva235.controlador_habitos.data.remote.dto.RegisterRequest
import com.renansilva235.controlador_habitos.data.remote.dto.RegisterResponse
import com.renansilva235.controlador_habitos.data.remote.retrofit.RetrofitInstance
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {
    @POST("/user/register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    companion object {
        val instance = RetrofitInstance.retrofit.create(RegisterApi::class.java)
    }
}