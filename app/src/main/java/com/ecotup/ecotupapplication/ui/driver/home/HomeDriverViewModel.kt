package com.ecotup.ecotupapplication.ui.driver.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ecotup.ecotupapplication.data.model.DriverModelData
import com.ecotup.ecotupapplication.data.model.PersonModelData
import com.ecotup.ecotupapplication.data.repository.EcotupRepository
import com.ecotup.ecotupapplication.data.repository.FindDriverRepository
import kotlinx.coroutines.launch

class HomeDriverViewModel(private val repository : EcotupRepository) : ViewModel()
{
    fun getSessionDriver() : LiveData<DriverModelData> = repository.getSessionDriver().asLiveData()
    fun getJobDriverOnGoingOneTime(idDriver : String) = repository.getJobDriverOnGoingOneTime(idDriver)
    fun getDetailUser(id : String) = repository.getDetailUser(id)

    fun setSessionUser(user : PersonModelData) = viewModelScope.launch { repository.setSessionUser(user) }
    fun getDetailDriver(id : String) = repository.getDetailDriver(id)
    fun setSessionDriver(driver : DriverModelData) = viewModelScope.launch { repository.setSessionDriver(driver) }

}

class HomeDriverViewModel2(private val repository: FindDriverRepository) : ViewModel()
{
    fun getClusteringAndSorting() = repository.getClusteringAndSorting()
}