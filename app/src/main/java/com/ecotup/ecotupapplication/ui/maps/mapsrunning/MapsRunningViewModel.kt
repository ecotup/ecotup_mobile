package com.ecotup.ecotupapplication.ui.maps.mapsrunning

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecotup.ecotupapplication.data.model.FindDriverModel
import com.ecotup.ecotupapplication.data.repository.EcotupRepository
import com.ecotup.ecotupapplication.data.repository.FindDriverRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MapsRunningViewModel(private val repository: FindDriverRepository) : ViewModel() {

    fun getDriver() : Flow<FindDriverModel> =
        repository.getDriver()
}

class MapsRunningViewModel2(private val repository: EcotupRepository) : ViewModel() {
    suspend fun getLatLongDriver(user_id : String, driver_id : String) =
        repository.getLatLongDriver(user_id, driver_id)

}