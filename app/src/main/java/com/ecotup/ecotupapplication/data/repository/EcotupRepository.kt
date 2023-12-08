package com.ecotup.ecotupapplication.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.ecotup.ecotupapplication.data.cammon.Result
import com.ecotup.ecotupapplication.data.model.PersonModel
import com.ecotup.ecotupapplication.data.network.ApiService
import com.ecotup.ecotupapplication.data.preferences.TokenPreferences
import com.ecotup.ecotupapplication.data.response.LoginDriverResponse
import com.ecotup.ecotupapplication.data.response.LoginResponse
import com.ecotup.ecotupapplication.data.response.RegisterDriverResponse
import com.ecotup.ecotupapplication.data.response.RegisterResponse

class EcotupRepository constructor(
    private val apiService: ApiService,
    private val preferences: TokenPreferences
) {

    // Preferences
    suspend fun setSessionToken(person: PersonModel) = preferences.setSessionToken(person)
    suspend fun logout() = preferences.logout()
    fun getSessionToken() = preferences.getSessionToken()

    // Login User (butuh suspend)
    fun setLogin(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.setLogin(email, password)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    // Login Driver (butuh suspend)
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


}