package com.ecotup.ecotupapplication.ui.maps.mapsdriver

import androidx.lifecycle.ViewModel
import com.ecotup.ecotupapplication.data.repository.EcotupRepository
import com.ecotup.ecotupapplication.data.repository.FindDriverRepository

class MapsDriverOneTimeViewModel(private val repository: FindDriverRepository) : ViewModel() {

}

class MapsDriverOneTimeViewModel2(private val repository: EcotupRepository) : ViewModel() {
    suspend fun getTransaksiByIdTransaksi(idTransaksi: String) =
        repository.getTransaksiByIdTransaksi(idTransaksi)

    suspend fun updatePointDriver(id: String, point: Int) = repository.updatePointDriver(id, point)

    suspend fun updateStatusTransaksi(idTransaction: String, status: String) =
        repository.updateStatusTransaksi(idTransaction, status)

    suspend fun updateLatLongDriver(idTransaction: String, latitude: Double, longitude: Double) =
        repository.updateLatLongDriver(idTransaction, latitude, longitude)

}