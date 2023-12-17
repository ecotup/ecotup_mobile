package com.ecotup.ecotupapplication.ui.general.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecotup.ecotupapplication.data.model.DriverModelData
import com.ecotup.ecotupapplication.data.model.PersonModel
import com.ecotup.ecotupapplication.data.model.PersonModelData
import com.ecotup.ecotupapplication.data.repository.EcotupRepository
import kotlinx.coroutines.launch

class LoginViewModel (private val repository: EcotupRepository) : ViewModel()
{
    fun setSessionToken(person : PersonModel) = viewModelScope.launch { repository.setSessionToken(person) }
    fun setSessionUser(user : PersonModelData) = viewModelScope.launch { repository.setSessionUser(user) }
    fun setSessionDriver(driver : DriverModelData) = viewModelScope.launch { repository.setSessionDriver(driver) }

    fun deleteSessionUser() = viewModelScope.launch { repository.deleteSessionUser() }
    fun deleteSessionDriver() = viewModelScope.launch { repository.deleteSessionDriver() }
    fun logout() = viewModelScope.launch { repository.logout() }
    fun setLogin(email : String, password : String) = repository.setLogin(email, password)
    fun setLoginDriver(email : String, password : String) = repository.setLoginDriver(email, password)
    fun getDetailUser(id : String) = repository.getDetailUser(id)
    fun getDetailDriver(id : String) = repository.getDetailDriver(id)

}