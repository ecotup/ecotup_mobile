package com.ecotup.ecotupapplication.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ecotup.ecotupapplication.data.model.PersonModel
import com.ecotup.ecotupapplication.data.model.PersonModelData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PersonPreferences private constructor(private val datastore: DataStore<Preferences>) {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "datauser")
    suspend fun setSessionData(personModelData: PersonModelData) {
        datastore.edit { pref ->
            pref[ID] = personModelData.id
            pref[NAME] = personModelData.name
            pref[EMAIL] = personModelData.email
            pref[PHONE] = personModelData.phone
            pref[LAT] = personModelData.lat
            pref[LONG] = personModelData.long
            pref[PROFILE] = personModelData.profile
            pref[POINT] = personModelData.point
            pref[SUBSCRIPTION_DATE] = personModelData.subscription_date
            pref[SUBSCRIPTION_STATUS] = personModelData.subscription_status
            pref[SUBSCRIPTION_VALUE] = personModelData.subscription_value
        }
    }

    fun getSessionData(): Flow<PersonModelData> {
        return datastore.data.map { pref ->
            PersonModelData(
                pref[ID] ?: "",
                pref[NAME] ?: "",
                pref[EMAIL] ?: "",
                pref[PHONE] ?: "",
                pref[LAT] ?: "",
                pref[LONG] ?: "",
                pref[PROFILE] ?: "",
                pref[POINT] ?: "0",
                pref[SUBSCRIPTION_DATE] ?: "",
                pref[SUBSCRIPTION_STATUS] ?: "",
                pref[SUBSCRIPTION_VALUE] ?: "",
            )
        }
    }

    suspend fun deleteSessionData() {
        datastore.edit { pref ->
            pref[ID] = ""
            pref[NAME] = ""
            pref[EMAIL] = ""
            pref[PHONE] = ""
            pref[LAT] = ""
            pref[LONG] = ""
            pref[PROFILE] = ""
            pref[POINT] = "0"
            pref[SUBSCRIPTION_DATE] = ""
            pref[SUBSCRIPTION_STATUS] = ""
            pref[SUBSCRIPTION_VALUE] = ""
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: PersonPreferences? = null
        private val ID = stringPreferencesKey("id")
        private val NAME = stringPreferencesKey("name")
        private val EMAIL = stringPreferencesKey("email")
        private val PHONE = stringPreferencesKey("phone")
        private val LAT = stringPreferencesKey("lat")
        private val LONG = stringPreferencesKey("long")
        private val PROFILE = stringPreferencesKey("profile")
        private val POINT = stringPreferencesKey("point")
        private val SUBSCRIPTION_DATE = stringPreferencesKey("subscription_date")
        private val SUBSCRIPTION_STATUS = stringPreferencesKey("subscription_status")
        private val SUBSCRIPTION_VALUE = stringPreferencesKey("subscription_value")

        fun getInstance(datastore: DataStore<Preferences>): PersonPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = PersonPreferences(datastore)
                INSTANCE = instance
                instance
            }
        }
    }
}