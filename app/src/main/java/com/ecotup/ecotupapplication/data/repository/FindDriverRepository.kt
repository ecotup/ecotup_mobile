package com.ecotup.ecotupapplication.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.ecotup.ecotupapplication.data.cammon.Result
import com.ecotup.ecotupapplication.data.model.FindDriverModel
import com.ecotup.ecotupapplication.data.network.ApiService
import com.ecotup.ecotupapplication.data.preferences.FindDriverPreferences
import com.ecotup.ecotupapplication.data.response.ClusterResponse
import com.ecotup.ecotupapplication.data.response.LatLongUpdateResponse
import com.ecotup.ecotupapplication.data.response.OneTimeResponse
import com.ecotup.ecotupapplication.data.response.TransaksiInsertResponse

class FindDriverRepository constructor(
    private val apiService: ApiService,
    private val prefData: FindDriverPreferences
) {
    fun findDriver(id: Int): LiveData<Result<OneTimeResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.findDriver(id)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getClusteringAndSorting() : LiveData<Result<ClusterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getClusteringAndSorting()
            emit(Result.Success(response))
        }
        catch (e : Exception)
        {
            emit(Result.Error(e.message.toString()))
        }
    }
    suspend fun setDriver(driver: FindDriverModel) = prefData.setDriverOneTime(driver)
    fun getDriver() = prefData.getDriverOneTime()
    suspend fun deleteDriver() = prefData.deleteDriverOneTime()

}