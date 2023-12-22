package com.ecotup.ecotupapplication.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ecotup.ecotupapplication.data.model.DriverModelData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DriverPreferences private constructor(private val datastore: DataStore<Preferences>) {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data")
    suspend fun setSessionDriver(personModelData: DriverModelData) {
        datastore.edit { pref ->
            pref[ID] = personModelData.id
            pref[NAME] = personModelData.name
            pref[EMAIL] = personModelData.email
            pref[PHONE] = personModelData.phone
            pref[LAT] = personModelData.lat
            pref[LONG] = personModelData.long
            pref[PROFILE] = personModelData.profile
            pref[POINT] = personModelData.point
            pref[TYPE] = personModelData.type
            pref[LICENSE] = personModelData.license
            pref[RATING] = personModelData.rating
        }
    }

    fun getSessionDriver(): Flow<DriverModelData> {
        return datastore.data.map { pref ->
            DriverModelData(
                pref[ID] ?: "",
                pref[NAME] ?: "",
                pref[EMAIL] ?: "",
                pref[PHONE] ?: "",
                pref[LAT] ?: "",
                pref[LONG] ?: "",
                pref[PROFILE] ?: "",
                pref[POINT] ?: "0",
                pref[TYPE] ?: "",
                pref[LICENSE] ?: "",
                pref[RATING] ?: ""
            )
        }
    }

    suspend fun deleteSessionDriver() {
        datastore.edit { pref ->
            pref[ID] = ""
            pref[NAME] = ""
            pref[EMAIL] = ""
            pref[PHONE] = ""
            pref[LAT] = ""
            pref[LONG] = ""
            pref[PROFILE] = ""
            pref[POINT] = "0"
            pref[TYPE] = ""
            pref[LICENSE] = ""
            pref[RATING] = ""
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: DriverPreferences? = null
        private val ID = stringPreferencesKey("id")
        private val NAME = stringPreferencesKey("name")
        private val EMAIL = stringPreferencesKey("email")
        private val PHONE = stringPreferencesKey("phone")
        private val LAT = stringPreferencesKey("lat")
        private val LONG = stringPreferencesKey("long")
        private val PROFILE = stringPreferencesKey("profile")
        private val POINT = stringPreferencesKey("point")
        private val TYPE = stringPreferencesKey("type")
        private val LICENSE = stringPreferencesKey("license")
        private val RATING = stringPreferencesKey("rating")

        fun getInstance(datastore: DataStore<Preferences>): DriverPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = DriverPreferences(datastore)
                INSTANCE = instance
                instance
            }
        }
    }
}