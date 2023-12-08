package com.ecotup.ecotupapplication.ui.user.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecotup.ecotupapplication.data.repository.EcotupRepository
import kotlinx.coroutines.launch

class SettingUserViewModel(private val repository : EcotupRepository) : ViewModel() {
    fun logoutUser() = viewModelScope.launch { repository.logout() }
}