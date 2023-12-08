package com.ecotup.ecotupapplication.ui.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ecotup.ecotupapplication.data.model.PersonModel
import com.ecotup.ecotupapplication.data.repository.EcotupRepository

class NavigationViewModel(private val repository: EcotupRepository) : ViewModel() {
    fun getSessionToken(): LiveData<PersonModel> = repository.getSessionToken().asLiveData()

}