package com.ecotup.ecotupapplication.data.vmf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ecotup.ecotupapplication.data.repository.EcotupRepository
import com.ecotup.ecotupapplication.ui.general.login.LoginViewModel
import com.ecotup.ecotupapplication.ui.user.registerUser.RegisterUserViewModel

class ViewModelFactory(private val repository: EcotupRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(RegisterUserViewModel::class.java)) {
            return RegisterUserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}