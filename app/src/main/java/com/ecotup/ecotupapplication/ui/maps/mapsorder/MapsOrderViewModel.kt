package com.ecotup.ecotupapplication.ui.maps.mapsorder

import androidx.lifecycle.ViewModel
import com.ecotup.ecotupapplication.data.model.FindDriverModel
import com.ecotup.ecotupapplication.data.repository.EcotupRepository
import com.ecotup.ecotupapplication.data.repository.FindDriverRepository
import kotlinx.coroutines.flow.Flow

class MapsOrderViewModel(private val repository: FindDriverRepository) : ViewModel() {
    fun getDriver(): Flow<FindDriverModel> =
        repository.getDriver()
}

class MapsOrderViewModel2 (private val repository: EcotupRepository) :ViewModel(){
    suspend fun insertTransaksi(
        driver_id: String,
        user_id: String,
        description: String,
        total_payment: Double,
        total_weight: Double,
        total_point: Int,
        status: String
    ) = repository.insertTransaksi(
        driver_id,
        user_id,
        description,
        total_payment,
        total_weight,
        total_point,
        status
    )
}
