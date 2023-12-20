package com.ecotup.ecotupapplication.data.network

import com.ecotup.ecotupapplication.data.response.ArticleResponse
import com.ecotup.ecotupapplication.data.response.DriverByIdResponse
import com.ecotup.ecotupapplication.data.response.LoginDriverResponse
import com.ecotup.ecotupapplication.data.response.LoginResponse
import com.ecotup.ecotupapplication.data.response.PointResponse
import com.ecotup.ecotupapplication.data.response.RegisterDriverResponse
import com.ecotup.ecotupapplication.data.response.RegisterResponse
import com.ecotup.ecotupapplication.data.response.UpdateProfileUserResponse
import com.ecotup.ecotupapplication.data.response.UpdateSubscriptionResponse
import com.ecotup.ecotupapplication.data.response.UserByIdResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

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
        @Field("phone") phone: String,
        @Field("longitude") longitude: Double,
        @Field("latitude") latitude: Double,
    ): RegisterResponse

    // Register Driver
    @FormUrlEncoded
    @POST("/api/driver/register")
    suspend fun setRegisterDriver(
        @Field("name") name: String,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("longitude") longitude: Double,
        @Field("latitude") latitude: Double,
        @Field("type") type: String,
        @Field("license") license: String,
    ): RegisterDriverResponse

    // Detail User
    @GET("/api/user/detail/{id}")
    suspend fun getDetailUser(
        @Path("id") id: String
    ): UserByIdResponse

    // Detail Driver
    @GET("/api/driver/detail/{id}")
    suspend fun getDetailDriver(
        @Path("id") id: String
    ): DriverByIdResponse

    // Article
    @GET("/api/article")
    suspend fun getArticle(): ArticleResponse

    // Subscription
    @FormUrlEncoded
    @POST("/api/user/update/subscription/{id}")
    suspend fun updateSubscription(
        @Path("id") id : String,
        @Field("subscription_id") subscriptionId : String
    ) : UpdateSubscriptionResponse

    @FormUrlEncoded
    @POST("/api/user/update/point/{id}")
    suspend fun updatePoint(
        @Path("id") id : String,
        @Field("point") point : Int
    ) : PointResponse

    // Update User
    @FormUrlEncoded
    @PUT("/api/user/update/{id}")
    suspend fun updateProfileUser(
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("phone") phone: String
    ): UpdateProfileUserResponse

}