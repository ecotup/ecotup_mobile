package com.ecotup.ecotupapplication.data.network

import com.ecotup.ecotupapplication.data.response.LoginResponse
import com.ecotup.ecotupapplication.data.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    // Login
    @FormUrlEncoded
    @POST("auth")
    suspend fun setLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    // Register
    @FormUrlEncoded
    @POST("register")
    suspend fun setRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("longitude") longitude : Double,
        @Field("latitude") latitude : Double,
    ): RegisterResponse

}