package com.ecotup.ecotupapplication.di

import android.content.Context
import com.ecotup.ecotupapplication.data.network.ApiConfig
import com.ecotup.ecotupapplication.data.network.ApiService
import com.ecotup.ecotupapplication.data.preferences.TokenPreferences
import com.ecotup.ecotupapplication.data.preferences.dataStore
import com.ecotup.ecotupapplication.data.repository.EcotupRepository
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): EcotupRepository {
        val pref = TokenPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return EcotupRepository(apiService, pref)
    }
}