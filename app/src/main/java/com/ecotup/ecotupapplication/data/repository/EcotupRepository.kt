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
import com.ecotup.ecotupapplication.data.response.DetailFinishTransactionResponse
import com.ecotup.ecotupapplication.data.response.DriverByIdResponse
import com.ecotup.ecotupapplication.data.response.GetTransaksiByIdTransaksiResponse
import com.ecotup.ecotupapplication.data.response.JobDriverOnGoingOneTimeResponse
import com.ecotup.ecotupapplication.data.response.LatLongUpdateResponse
import com.ecotup.ecotupapplication.data.response.LoginDriverResponse
import com.ecotup.ecotupapplication.data.response.LoginResponse
import com.ecotup.ecotupapplication.data.response.PointResponse
import com.ecotup.ecotupapplication.data.response.RegisterDriverResponse
import com.ecotup.ecotupapplication.data.response.RegisterResponse
import com.ecotup.ecotupapplication.data.response.RewardResponse
import com.ecotup.ecotupapplication.data.response.TransaksiInsertResponse
import com.ecotup.ecotupapplication.data.response.UpdateLatLongDriverResponse
import com.ecotup.ecotupapplication.data.response.UpdateProfileDriverResponse
import com.ecotup.ecotupapplication.data.response.UpdateProfileUserResponse
import com.ecotup.ecotupapplication.data.response.UpdateRatingResponse
import com.ecotup.ecotupapplication.data.response.UpdateStatusTransactionResponse
import com.ecotup.ecotupapplication.data.response.UpdateSubscriptionResponse
import com.ecotup.ecotupapplication.data.response.UserByIdResponse

class EcotupRepository constructor(
    private val apiService: ApiService,
    private val preferences: TokenPreferences,
    private val prefUser: PersonPreferences,
    private val prefDriver: DriverPreferences
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
        type: String,
        license: String
    ): LiveData<Result<RegisterDriverResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.setRegisterDriver(
                name,
                password,
                email,
                phone,
                longitude,
                latitude,
                type,
                license
            )
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    // Get Detail User
    fun getDetailUser(id: String): LiveData<Result<UserByIdResponse>> = liveData {
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
    fun getDetailDriver(id: String): LiveData<Result<DriverByIdResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getDetailDriver(id)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    // Get Article
    fun getArticle(): LiveData<Result<ArticleResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getArticle()
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }


    // Get Reward
    fun getReward(): LiveData<Result<RewardResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getReward()
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }


    // Update Subscription
    suspend fun updateSubscription(
        id: String,
        subscriptionId: String
    ): LiveData<Result<UpdateSubscriptionResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.updateSubscription(id = id, subscriptionId = subscriptionId)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    // Update Point
    suspend fun updatePoint(id: String, point: Int): LiveData<Result<PointResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.updatePoint(id = id, point = point)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    // Update Profile User
    suspend fun updateProfileUser(
        id: String,
        email: String,
        name: String,
        phone: String,
    ): LiveData<Result<UpdateProfileUserResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.updateProfileUser(id, email, name, phone)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    // Update Profile User
    suspend fun updateProfileDriver(
        id: String,
        email: String,
        name: String,
        phone: String,
        license: String,
        type: String
    ): LiveData<Result<UpdateProfileDriverResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.updateProfileDriver(id, email, name, phone, license, type)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    // Update Point Driver
    suspend fun updatePointDriver(id: String, point: Int): LiveData<Result<PointResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.updatePointDriver(id = id, point = point)
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    // Update Status Transaksi
    suspend fun updateStatusTransaksi(
        idTransaction: String,
        status: String
    ): LiveData<Result<UpdateStatusTransactionResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.updateStatusTransaction(idTransaction, status)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    // Insert Transaksi
    suspend fun insertTransaksi(
        driver_id: String,
        user_id: String,
        description: String,
        total_payment: Double,
        total_weight: Double,
        total_point: Int,
        status: String
    ): LiveData<Result<TransaksiInsertResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.insertTransaksi(
                driver_id,
                user_id,
                description,
                total_payment,
                total_weight,
                total_point,
                status
            )
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    // Get Lat Long Driver
    suspend fun getLatLongDriver(
        user_id: String,
        driver_id: String
    ): LiveData<Result<LatLongUpdateResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getLatLongDriver(user_id, driver_id)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    // Get Detail Transaction
    suspend fun getDetailTransactionFinish(id: String): LiveData<Result<DetailFinishTransactionResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.getDetailFinishTransaction(id)
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    // Update Rating
    suspend fun updateRating(
        idDriver: String,
        rating: Int
    ): LiveData<Result<UpdateRatingResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.updateRating(idDriver, rating)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    // Get Job Driver On Going One Time
    fun getJobDriverOnGoingOneTime(id: String): LiveData<Result<JobDriverOnGoingOneTimeResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.getJobDriverOnGoingOneTime(id)
                emit(Result.Success(response))
                Log.d("JOB DRIVER ONE TIME", response.data.toString())
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    // Get Transaksi By Id Transaksi
    suspend fun getTransaksiByIdTransaksi(idTransaksi: String): LiveData<Result<GetTransaksiByIdTransaksiResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.getTransaksiByIdTransaksi(idTransaksi)
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    // Update Lat Long Driver
    suspend fun updateLatLongDriver(
        idTransaksi: String,
        latitude: Double,
        longitude: Double
    ): LiveData<Result<UpdateLatLongDriverResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.updateLatLongDriver(idTransaksi, latitude, longitude)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
}