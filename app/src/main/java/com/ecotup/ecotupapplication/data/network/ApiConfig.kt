package com.ecotup.ecotupapplication.data.network

import com.ecotup.ecotupapplication.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        private const val BASE_URL = BuildConfig.URL
        private const val BASE_URL_SERVICE = BuildConfig.URL_SERVICE
        private const val BASE_TIMEZONE = BuildConfig.URL_TIMEZONE
        fun getApiService(url : String): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }

        fun getDefaultApi() : ApiService{
            return getApiService(BASE_URL)
        }

        fun getServiceApiService() : ApiService{
            return getApiService(BASE_URL_SERVICE)
        }

        fun getServiceApiTimeZone() : ApiService{
            return getApiService(BASE_TIMEZONE)
        }
    }
}