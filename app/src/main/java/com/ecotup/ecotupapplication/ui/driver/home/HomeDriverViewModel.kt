package com.ecotup.ecotupapplication.ui.driver.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ecotup.ecotupapplication.data.model.DriverModelData
import com.ecotup.ecotupapplication.data.repository.EcotupRepository

class HomeDriverViewModel(private val repository : EcotupRepository) : ViewModel()
{
    fun getSessionDriver() : LiveData<DriverModelData> = repository.getSessionDriver().asLiveData()
}