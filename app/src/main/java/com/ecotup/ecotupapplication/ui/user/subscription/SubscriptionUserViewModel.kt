package com.ecotup.ecotupapplication.ui.user.subscription

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ecotup.ecotupapplication.data.model.PersonModelData
import com.ecotup.ecotupapplication.data.repository.EcotupRepository

class SubscriptionUserViewModel(private val repository: EcotupRepository) : ViewModel() {

    suspend fun updateSubscription(id: String, subscriptionId: String) =
        repository.updateSubscription(id, subscriptionId)

    fun getSessionUser(): LiveData<PersonModelData> = repository.getSessionUser().asLiveData()
}