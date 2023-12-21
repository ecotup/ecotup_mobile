package com.ecotup.ecotupapplication.di

import android.content.Context
import com.ecotup.ecotupapplication.data.network.ApiConfig
import com.ecotup.ecotupapplication.data.network.ApiService
import com.ecotup.ecotupapplication.data.preferences.DriverPreferences
import com.ecotup.ecotupapplication.data.preferences.FindDriverPreferences
import com.ecotup.ecotupapplication.data.preferences.PersonPreferences
import com.ecotup.ecotupapplication.data.preferences.TokenPreferences
import com.ecotup.ecotupapplication.data.preferences.dataStore
import com.ecotup.ecotupapplication.data.repository.EcotupRepository
import com.ecotup.ecotupapplication.data.repository.FindDriverRepository
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): EcotupRepository {
        val pref = TokenPreferences.getInstance(context.dataStore)
        val prefData = PersonPreferences.getInstance(context.dataStore)
        val prefDriver = DriverPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getDefaultApi()
        return EcotupRepository(apiService, pref, prefData, prefDriver)
    }

    fun provideFindDriverRepository(context : Context) : FindDriverRepository{
        val prefData = FindDriverPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getServiceApiService()
        return FindDriverRepository(apiService, prefData)
    }
}