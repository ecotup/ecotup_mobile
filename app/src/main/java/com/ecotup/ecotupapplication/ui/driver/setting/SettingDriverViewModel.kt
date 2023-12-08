package com.ecotup.ecotupapplication.ui.driver.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecotup.ecotupapplication.data.repository.EcotupRepository
import kotlinx.coroutines.launch

class SettingDriverViewModel(private val repository : EcotupRepository) : ViewModel(){
    fun logoutDriver() = viewModelScope.launch { repository.logout() }
}