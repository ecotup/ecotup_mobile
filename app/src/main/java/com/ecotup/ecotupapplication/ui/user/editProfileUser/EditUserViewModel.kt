package com.ecotup.ecotupapplication.ui.user.editProfileUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ecotup.ecotupapplication.data.model.PersonModelData
import com.ecotup.ecotupapplication.data.repository.EcotupRepository
import kotlinx.coroutines.launch

class EditUserViewModel (private val repository : EcotupRepository) : ViewModel(){
    fun getSessionUser() : LiveData<PersonModelData> = repository.getSessionUser().asLiveData()
    fun setSessionUser(user : PersonModelData) = viewModelScope.launch { repository.setSessionUser(user) }
    fun getDetailUser(id : String) = repository.getDetailUser(id)

    suspend fun updateProfileUser(
        name: String,
//        password: String,
        email: String,
        phone: String
//        latitude: Double,
//        longitude: Double
    ) =
        repository.updateProfileUser(name, email, phone)
}