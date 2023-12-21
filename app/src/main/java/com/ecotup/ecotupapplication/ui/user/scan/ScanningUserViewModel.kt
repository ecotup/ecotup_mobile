package com.ecotup.ecotupapplication.ui.user.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ecotup.ecotupapplication.data.model.PersonModelData
import com.ecotup.ecotupapplication.data.repository.EcotupRepository

class ScanningUserViewModel(private val repository : EcotupRepository) : ViewModel()
{
    suspend fun updatePoint(id : String, point : Int) = repository.updatePoint(id, point)
    fun getSessionUser() : LiveData<PersonModelData> = repository.getSessionUser().asLiveData()
}