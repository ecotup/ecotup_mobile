package com.ecotup.ecotupapplication.ui.general.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecotup.ecotupapplication.data.model.PersonModel
import com.ecotup.ecotupapplication.data.repository.EcotupRepository
import kotlinx.coroutines.launch

class LoginViewModel (private val repository: EcotupRepository) : ViewModel()
{
    fun setSessionToken(person : PersonModel) = viewModelScope.launch { repository.setSessionToken(person) }
    fun setLogin(email : String, password : String) = repository.setLogin(email, password)
    fun setLoginDriver(email : String, password : String) = repository.setLoginDriver(email, password)

}