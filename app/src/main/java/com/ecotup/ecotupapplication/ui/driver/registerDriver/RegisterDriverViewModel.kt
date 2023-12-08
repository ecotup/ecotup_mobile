package com.ecotup.ecotupapplication.ui.driver.registerDriver

import androidx.lifecycle.ViewModel
import com.ecotup.ecotupapplication.data.repository.EcotupRepository

class RegisterDriverViewModel (private val repository: EcotupRepository) : ViewModel(){

    // Tampung Data
    suspend fun setRegisterDriver(
        name: String,
        password: String,
        email: String,
        phone: String,
        latitude: Double,
        longitude: Double,
        type: String,
        license: String
    ) =
        repository.setRegisterDriver(name, password, email, phone, latitude, longitude, type, license)
}