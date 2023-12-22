package com.ecotup.ecotupapplication.ui.driver.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ecotup.ecotupapplication.data.model.PersonModelData
import com.ecotup.ecotupapplication.data.repository.EcotupRepository
import kotlinx.coroutines.launch

class SettingDriverViewModel(private val repository: EcotupRepository) : ViewModel() {
    fun getSessionUser(): LiveData<PersonModelData> = repository.getSessionUser().asLiveData()
    fun setSessionUser(user: PersonModelData) =
        viewModelScope.launch { repository.setSessionUser(user) }

    fun getDetailUser(id: String) = repository.getDetailUser(id)
    fun logoutDriver() = viewModelScope.launch { repository.logout() }
    fun deleteSessionDriver() = viewModelScope.launch { repository.deleteSessionDriver() }

}