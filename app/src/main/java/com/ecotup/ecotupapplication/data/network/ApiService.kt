package com.ecotup.ecotupapplication.data.network

import com.ecotup.ecotupapplication.data.response.LoginDriverResponse
import com.ecotup.ecotupapplication.data.response.LoginResponse
import com.ecotup.ecotupapplication.data.response.RegisterDriverResponse
import com.ecotup.ecotupapplication.data.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    // Login User
    @FormUrlEncoded
    @POST("/api/user/login")
    suspend fun setLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    // Login Driver
    @FormUrlEncoded
    @POST("/api/driver/login")
    suspend fun setLoginDriver(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginDriverResponse

    // Register User
    @FormUrlEncoded
    @POST("/api/user/register")
    suspend fun setRegister(
        @Field("name") name: String,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("phone") phone : String,
        @Field("longitude") longitude : Double,
        @Field("latitude") latitude : Double,
    ): RegisterResponse

    // Register Driver
    @FormUrlEncoded
    @POST("/api/driver/register")
    suspend fun setRegisterDriver(
        @Field("name") name: String,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("phone") phone : String,
        @Field("longitude") longitude : Double,
        @Field("latitude") latitude : Double,
        @Field("type") type : String,
        @Field("license") license : String,
    ): RegisterDriverResponse

}