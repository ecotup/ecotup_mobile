package com.ecotup.ecotupapplication.ui.user.registerUser

import androidx.lifecycle.ViewModel
import com.ecotup.ecotupapplication.data.repository.EcotupRepository

class RegisterUserViewModel(private val repository: EcotupRepository) : ViewModel() {
    // Tampung Data
    suspend fun setRegisterUser(
        name: String,
        password: String,
        email: String,
        phone: String,
        latitude: Double,
        longitude: Double
    ) =
        repository.setRegisterUser(name, password, email, phone, latitude, longitude)

}