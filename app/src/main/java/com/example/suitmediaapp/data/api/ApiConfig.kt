package com.example.suitmediaapp.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private const val AUTH_TOKEN = "https://reqres.in/" // Ganti "API_KEY" dengan API key yang sebenarnya

    fun getApiService(): ApiService {
        // Logging interceptor untuk debugging
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // Membuat instance OkHttpClient dengan interceptor yang ditambahkan
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        // Membuat instance Retrofit dengan OkHttpClient dan konverter Gson
        val retrofit = Retrofit.Builder()
            .baseUrl(AUTH_TOKEN) // Ganti dengan base URL API yang digunakan
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}
