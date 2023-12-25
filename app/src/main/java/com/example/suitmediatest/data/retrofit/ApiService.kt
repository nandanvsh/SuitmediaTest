package com.example.suitmediatest.data.retrofit

import com.example.suitmediatest.data.response.UserAccountResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getAllUsers(
        @Query("page") page: Int? = null,
        @Query("per_page") perPage: Int? = null
    ): UserAccountResponse

}