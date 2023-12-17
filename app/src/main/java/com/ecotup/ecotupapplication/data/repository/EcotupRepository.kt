package com.ecotup.ecotupapplication.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.ecotup.ecotupapplication.data.cammon.Result
import com.ecotup.ecotupapplication.data.model.DriverModelData
import com.ecotup.ecotupapplication.data.model.PersonModel
import com.ecotup.ecotupapplication.data.model.PersonModelData
import com.ecotup.ecotupapplication.data.network.ApiService
import com.ecotup.ecotupapplication.data.preferences.DriverPreferences
import com.ecotup.ecotupapplication.data.preferences.PersonPreferences
import com.ecotup.ecotupapplication.data.preferences.TokenPreferences
import com.ecotup.ecotupapplication.data.response.ArticleResponse
import com.ecotup.ecotupapplication.data.response.DriverByIdResponse
import com.ecotup.ecotupapplication.data.response.LoginDriverResponse
import com.ecotup.ecotupapplication.data.response.LoginResponse
import com.ecotup.ecotupapplication.data.response.PointResponse
import com.ecotup.ecotupapplication.data.response.RegisterDriverResponse
import com.ecotup.ecotupapplication.data.response.RegisterResponse
import com.ecotup.ecotupapplication.data.response.UpdateSubscriptionResponse
import com.ecotup.ecotupapplication.data.response.UserByIdResponse

class EcotupRepository constructor(
    private val apiService: ApiService,
    private val preferences: TokenPreferences,
    private val prefUser : PersonPreferences,
    private val prefDriver : DriverPreferences
) {

    // Preferences
    suspend fun setSessionToken(person: PersonModel) = preferences.setSessionToken(person)
    suspend fun setSessionDriver(driver: DriverModelData) = prefDriver.setSessionDriver(driver)
    suspend fun setSessionUser(person: PersonModelData) = prefUser.setSessionData(person)
    suspend fun logout() = preferences.logout()
    suspend fun deleteSessionUser() = prefUser.deleteSessionData()
    suspend fun deleteSessionDriver() = prefDriver.deleteSessionDriver()
    fun getSessionToken() = preferences.getSessionToken()
    fun getSessionUser() = prefUser.getSessionData()
    fun getSessionDriver() = prefDriver.getSessionDriver()


    // Login User
    fun setLogin(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.setLogin(email, password)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    // Login Driver
    fun setLoginDriver(email: String, password: String): LiveData<Result<LoginDriverResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.setLoginDriver(email, password)
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    // Register User
    suspend fun setRegisterUser(
        name: String,
        password: String,
        email: String,
        phone: String,
        latitude: Double,
        longitude: Double
    ): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.setRegister(name, password, email, phone, longitude, latitude)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    // Register Driver
    suspend fun setRegisterDriver(
        name: String,
        password: String,
        email: String,
        phone: String,
        latitude: Double,
        longitude: Double,
        type : String,
        license : String
    ): LiveData<Result<RegisterDriverResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.setRegisterDriver(name, password, email, phone, longitude, latitude, type, license)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    // Get Detail User
    fun getDetailUser(id: String) : LiveData<Result<UserByIdResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getDetailUser(id)
            Log.d("RESPONSE", response.data?.userName.toString())
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    // Get Detail Driver
    fun getDetailDriver(id: String) : LiveData<Result<DriverByIdResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getDetailDriver(id)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    // Get Article
    fun getArticle() : LiveData<Result<ArticleResponse>> = liveData{
        emit(Result.Loading)
        try {
            val response = apiService.getArticle()
            emit(Result.Success(response))
        }
        catch (e: Exception){
            emit(Result.Error(e.message.toString()))
        }
    }

    // Update Subscription
    suspend fun updateSubscription(id : String, subscriptionId : String) : LiveData<Result<UpdateSubscriptionResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.updateSubscription(id = id, subscriptionId = subscriptionId)
            emit(Result.Success(response))
        }
        catch (e : Exception)
        {
            emit(Result.Error(e.message.toString()))
        }
    }

    // Update Point
    suspend fun updatePoint(id : String, point : Int) : LiveData<Result<PointResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.updatePoint(id = id, point = point)
            emit(Result.Success(response))
        }
        catch (e : Exception)
        {
            emit(Result.Error(e.message.toString()))
        }
    }

}