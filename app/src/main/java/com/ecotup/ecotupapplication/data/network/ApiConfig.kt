package com.ecotup.ecotupapplication.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.ecotup.ecotupapplication.BuildConfig

class ApiConfig {
    companion object {
        // URL masih mw di perbaiki di bagian ini harusnya ditempatkan di file gradle.properties
        private const val BASE_URL = BuildConfig.URL
        fun getApiService(): ApiService {
            // Harus diperbaiki lagi saat tahap production
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}