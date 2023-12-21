package com.ecotup.ecotupapplication.data.vmf

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ecotup.ecotupapplication.data.repository.FindDriverRepository
import com.ecotup.ecotupapplication.di.Injection
import com.ecotup.ecotupapplication.ui.driver.home.HomeDriverViewModel2
import com.ecotup.ecotupapplication.ui.maps.mapscheck.MapsCheckViewModel
import com.ecotup.ecotupapplication.ui.maps.mapsdriver.MapsDriverOneTimeViewModel
import com.ecotup.ecotupapplication.ui.maps.mapsorder.MapsOrderViewModel
import com.ecotup.ecotupapplication.ui.maps.mapsrunning.MapsRunningViewModel

class ServiceViewModelFactory(private val repository: FindDriverRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MapsCheckViewModel::class.java) -> MapsCheckViewModel(
                repository
            ) as T

            modelClass.isAssignableFrom(MapsOrderViewModel::class.java) -> MapsOrderViewModel(
                repository
            ) as T

            modelClass.isAssignableFrom(MapsRunningViewModel::class.java) -> MapsRunningViewModel(
                repository
            ) as T
            modelClass.isAssignableFrom(MapsDriverOneTimeViewModel::class.java) -> MapsDriverOneTimeViewModel(
                repository
            ) as T

            modelClass.isAssignableFrom(HomeDriverViewModel2::class.java) -> HomeDriverViewModel2(
                repository
            ) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ServiceViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ServiceViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ServiceViewModelFactory::class.java) {
                    INSTANCE =
                        ServiceViewModelFactory(Injection.provideFindDriverRepository(context))
                }
            }
            return INSTANCE as ServiceViewModelFactory
        }
    }
}