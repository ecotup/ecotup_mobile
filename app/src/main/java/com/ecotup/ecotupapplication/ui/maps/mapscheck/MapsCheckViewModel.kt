package com.ecotup.ecotupapplication.ui.maps.mapscheck

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecotup.ecotupapplication.data.model.FindDriverModel
import com.ecotup.ecotupapplication.data.repository.FindDriverRepository
import kotlinx.coroutines.launch

class MapsCheckViewModel(private val repository: FindDriverRepository) : ViewModel() {
    fun findDriver(id: Int) =
        repository.findDriver(id = id)

    fun setDriver(driver: FindDriverModel) =
        viewModelScope.launch {
            repository.setDriver(driver = driver)
        }

    fun getDriver() =
        repository.getDriver()

    fun deleteDriver() = viewModelScope.launch {
        repository.deleteDriver()
    }
}