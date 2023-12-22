package com.ecotup.ecotupapplication.ui.driver.editProfileDriver

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ecotup.ecotupapplication.data.model.DriverModelData
import com.ecotup.ecotupapplication.data.repository.EcotupRepository
import kotlinx.coroutines.launch

class EditDriverViewModel(private val repository: EcotupRepository) : ViewModel() {
    fun getSessionDriver(): LiveData<DriverModelData> = repository.getSessionDriver().asLiveData()
    fun setSessionDriver(driver: DriverModelData) =
        viewModelScope.launch { repository.setSessionDriver(driver) }

    fun getDetailDriver(id: String) = repository.getDetailDriver(id)

    suspend fun updateProfileDriver(
        id: String,
        name: String,
        email: String,
        phone: String,
        license: String,
        type: String,
    ) =
        repository.updateProfileDriver(id, name, email, phone, license, type)
}