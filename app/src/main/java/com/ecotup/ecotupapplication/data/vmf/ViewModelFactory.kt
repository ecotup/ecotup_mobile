package com.ecotup.ecotupapplication.data.vmf

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ecotup.ecotupapplication.data.repository.EcotupRepository
import com.ecotup.ecotupapplication.di.Injection
import com.ecotup.ecotupapplication.ui.driver.editProfileDriver.EditDriverViewModel
import com.ecotup.ecotupapplication.ui.driver.home.HomeDriverViewModel
import com.ecotup.ecotupapplication.ui.driver.registerDriver.RegisterDriverViewModel
import com.ecotup.ecotupapplication.ui.driver.setting.SettingDriverViewModel
import com.ecotup.ecotupapplication.ui.general.login.LoginViewModel
import com.ecotup.ecotupapplication.ui.maps.mapsdriver.MapsDriverOneTimeViewModel2
import com.ecotup.ecotupapplication.ui.maps.mapsorder.MapsOrderViewModel2
import com.ecotup.ecotupapplication.ui.maps.mapsrunning.MapsRunningViewModel2
import com.ecotup.ecotupapplication.ui.navigation.NavigationViewModel
import com.ecotup.ecotupapplication.ui.user.editProfileUser.EditUserViewModel
import com.ecotup.ecotupapplication.ui.user.finishtransaction.FinishTransactionUserViewModel
import com.ecotup.ecotupapplication.ui.user.history.HistoryUserViewModel
import com.ecotup.ecotupapplication.ui.user.home.HomeUserViewModel
import com.ecotup.ecotupapplication.ui.user.registerUser.RegisterUserViewModel
import com.ecotup.ecotupapplication.ui.user.scan.ScanningUserViewModel
import com.ecotup.ecotupapplication.ui.user.setting.SettingUserViewModel
import com.ecotup.ecotupapplication.ui.user.subscription.SubscriptionUserViewModel

class ViewModelFactory(private val repository: EcotupRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(repository) as T
            modelClass.isAssignableFrom(RegisterUserViewModel::class.java) -> RegisterUserViewModel(repository) as T
            modelClass.isAssignableFrom(RegisterDriverViewModel::class.java) -> RegisterDriverViewModel(repository) as T
            modelClass.isAssignableFrom(HomeUserViewModel::class.java) -> HomeUserViewModel(repository) as T
            modelClass.isAssignableFrom(HomeDriverViewModel::class.java) -> HomeDriverViewModel(repository) as T
            modelClass.isAssignableFrom(HistoryUserViewModel::class.java) -> HistoryUserViewModel(repository) as T
            modelClass.isAssignableFrom(ScanningUserViewModel::class.java) -> ScanningUserViewModel(repository) as T
            modelClass.isAssignableFrom(SubscriptionUserViewModel::class.java) -> SubscriptionUserViewModel(repository) as T
            modelClass.isAssignableFrom(NavigationViewModel::class.java) -> NavigationViewModel(repository) as T
            modelClass.isAssignableFrom(SettingUserViewModel::class.java) -> SettingUserViewModel(repository) as T
            modelClass.isAssignableFrom(SettingDriverViewModel::class.java) -> SettingDriverViewModel(repository) as T
            modelClass.isAssignableFrom(EditUserViewModel::class.java) -> EditUserViewModel(repository) as T
            modelClass.isAssignableFrom(EditDriverViewModel::class.java) -> EditDriverViewModel(repository) as T
            modelClass.isAssignableFrom(RegisterUserViewModel::class.java) -> RegisterUserViewModel(
                repository
            ) as T

            modelClass.isAssignableFrom(RegisterDriverViewModel::class.java) -> RegisterDriverViewModel(
                repository
            ) as T

            modelClass.isAssignableFrom(HomeUserViewModel::class.java) -> HomeUserViewModel(
                repository
            ) as T

            modelClass.isAssignableFrom(HomeDriverViewModel::class.java) -> HomeDriverViewModel(
                repository
            ) as T


            modelClass.isAssignableFrom(HistoryUserViewModel::class.java) -> HistoryUserViewModel(
                repository
            ) as T

            modelClass.isAssignableFrom(ScanningUserViewModel::class.java) -> ScanningUserViewModel(
                repository
            ) as T

            modelClass.isAssignableFrom(SubscriptionUserViewModel::class.java) -> SubscriptionUserViewModel(
                repository
            ) as T

            modelClass.isAssignableFrom(NavigationViewModel::class.java) -> NavigationViewModel(
                repository
            ) as T

            modelClass.isAssignableFrom(SettingUserViewModel::class.java) -> SettingUserViewModel(
                repository
            ) as T

            modelClass.isAssignableFrom(SettingDriverViewModel::class.java) -> SettingDriverViewModel(
                repository
            ) as T

            modelClass.isAssignableFrom(MapsOrderViewModel2::class.java) -> MapsOrderViewModel2(
                repository
            ) as T

            modelClass.isAssignableFrom(MapsRunningViewModel2::class.java) -> MapsRunningViewModel2(
                repository
            ) as T

            modelClass.isAssignableFrom(FinishTransactionUserViewModel::class.java) -> FinishTransactionUserViewModel(
                repository
            ) as T

            modelClass.isAssignableFrom(MapsDriverOneTimeViewModel2::class.java) -> MapsDriverOneTimeViewModel2(
                repository
            ) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}