package com.ecotup.ecotupapplication.ui.user.subscription

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ecotup.ecotupapplication.data.model.PersonModelData
import com.ecotup.ecotupapplication.data.repository.EcotupRepository
import kotlinx.coroutines.launch

class SubscriptionUserViewModel(private val repository : EcotupRepository) : ViewModel() {

    suspend fun updateSubscription(id : String, subscriptionId : String) = repository.updateSubscription(id, subscriptionId)

    fun getSessionUser() : LiveData<PersonModelData> = repository.getSessionUser().asLiveData()
}