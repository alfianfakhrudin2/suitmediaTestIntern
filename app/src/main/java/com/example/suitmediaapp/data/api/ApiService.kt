package com.example.suitmediaapp.data.api

import com.example.suitmediaapp.data.response.ResponseUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/users")
    fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<ResponseUser>
}


