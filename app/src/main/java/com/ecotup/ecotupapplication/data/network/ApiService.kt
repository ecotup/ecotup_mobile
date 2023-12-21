package com.ecotup.ecotupapplication.data.network

import com.ecotup.ecotupapplication.data.response.ArticleResponse
import com.ecotup.ecotupapplication.data.response.ClusterResponse
import com.ecotup.ecotupapplication.data.response.DetailFinishTransactionResponse
import com.ecotup.ecotupapplication.data.response.DriverByIdResponse
import com.ecotup.ecotupapplication.data.response.GetTransaksiByIdTransaksiResponse
import com.ecotup.ecotupapplication.data.response.JobDriverOnGoingOneTimeResponse
import com.ecotup.ecotupapplication.data.response.LatLongUpdateResponse
import com.ecotup.ecotupapplication.data.response.LoginDriverResponse
import com.ecotup.ecotupapplication.data.response.LoginResponse
import com.ecotup.ecotupapplication.data.response.OneTimeResponse
import com.ecotup.ecotupapplication.data.response.PointResponse
import com.ecotup.ecotupapplication.data.response.RegisterDriverResponse
import com.ecotup.ecotupapplication.data.response.RegisterResponse
import com.ecotup.ecotupapplication.data.response.TransaksiInsertResponse
import com.ecotup.ecotupapplication.data.response.UpdateLatLongDriverResponse
import com.ecotup.ecotupapplication.data.response.UpdateProfileDriverResponse
import com.ecotup.ecotupapplication.data.response.UpdateProfileUserResponse
import com.ecotup.ecotupapplication.data.response.UpdateRatingResponse
import com.ecotup.ecotupapplication.data.response.UpdateStatusTransactionResponse
import com.ecotup.ecotupapplication.data.response.UpdateSubscriptionResponse
import com.ecotup.ecotupapplication.data.response.UserByIdResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
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
        @Path("id") id: String,
        @Field("subscription_id") subscriptionId: String
    ): UpdateSubscriptionResponse

    @FormUrlEncoded
    @POST("/api/user/update/point/{id}")
    suspend fun updatePoint(
        @Path("id") id: String,
        @Field("point") point: Int
    ): PointResponse

    @FormUrlEncoded
    @POST("/api/driver/update/point/{id}")
    suspend fun updatePointDriver(
        @Path("id") id: String,
        @Field("point") point: Int
    ): PointResponse

    // Find Nearest Driver
    @GET("/find_nearest_driver/{id}")
    suspend fun findDriver(
        @Path("id") id: Int
    ): OneTimeResponse

    // Clustering and Sorting
    @GET("/clustering_and_sorting")
    suspend fun getClusteringAndSorting(): ClusterResponse

    // Insert Transaksi
    @FormUrlEncoded
    @POST("/api/transaction/register")
    suspend fun insertTransaksi(
        @Field("driver_id") driver_id: String,
        @Field("user_id") user_id: String,
        @Field("description") description: String,
        @Field("total_payment") total_payment: Double,
        @Field("total_weight") total_weight: Double,
        @Field("total_point") total_point: Int,
        @Field("status") status: String,
    ): TransaksiInsertResponse


    // Update Lat Long driver
    @FormUrlEncoded
    @POST("/api/transaction/location/driver")
    suspend fun getLatLongDriver(
        @Field("user_id") user_id: String,
        @Field("driver_id") driver_id: String
    ): LatLongUpdateResponse

    //  GetDetailFinishTransaction
    @GET("/api/transaction/detail/{id}")
    suspend fun getDetailFinishTransaction(
        @Path("id") id: String
    ): DetailFinishTransactionResponse

    @FormUrlEncoded
    @POST("/api/driver/update/rating/{id}")
    suspend fun updateRating(
        @Path("id") id: String,
        @Field("rating") rating: Int
    ): UpdateRatingResponse


    @GET("/api/transaction/driver/status/{id}")
    suspend fun getJobDriverOnGoingOneTime(
        @Path("id") id: String
    ): JobDriverOnGoingOneTimeResponse

    @GET("/api/transaction/detail/{id}")
    suspend fun getTransaksiByIdTransaksi(
        @Path("id") id: String
    ): GetTransaksiByIdTransaksiResponse

    @FormUrlEncoded
    @POST("/api/transaction/update/status/{idTransaction}")
    suspend fun updateStatusTransaction(
        @Path("idTransaction") idTransaction: String,
        @Field("status") status: String
    ): UpdateStatusTransactionResponse

    // Update Lat Long Driver
    @FormUrlEncoded
    @POST("/api/transaction/update/location/driver/{idTransaction}")
    suspend fun updateLatLongDriver(
        @Path("idTransaction") idTransaction: String,
        @Field("latitude_start") latitude_start: Double,
        @Field("longitude_start") longitude_start: Double
    ): UpdateLatLongDriverResponse


    // Update User
    @FormUrlEncoded
    @PUT("/api/user/update/{id}")
    suspend fun updateProfileUser(
        @Path("id") id: String,
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("phone") phone: String
    ): UpdateProfileUserResponse

    // Update User
    @FormUrlEncoded
    @PUT("/api/driver/update/{id}")
    suspend fun updateProfileDriver(
        @Path("id") id: String,
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("phone") phone: String,
        @Field("license") license: String,
        @Field("type") type: String
    ): UpdateProfileDriverResponse

    // Get Transaction
    @FormUrlEncoded
    @POST("/api/transaction/detail")
    suspend fun getListTransaction(
        @Field("user_id") user_id: String,
        @Field("driver_id") driver_id: String
    ): GetTransaksiByIdTransaksiResponse

}