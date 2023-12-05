package com.ecotup.ecotupapplication.data.vmf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ecotup.ecotupapplication.data.repository.EcotupRepository
import com.ecotup.ecotupapplication.ui.driver.registerDriver.RegisterDriverViewModel
import com.ecotup.ecotupapplication.ui.general.login.LoginViewModel
import com.ecotup.ecotupapplication.ui.user.registerUser.RegisterUserViewModel

class ViewModelFactory(private val repository: EcotupRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(repository) as T
            modelClass.isAssignableFrom(RegisterUserViewModel::class.java) -> RegisterUserViewModel(repository) as T
            modelClass.isAssignableFrom(RegisterDriverViewModel::class.java) -> RegisterDriverViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}