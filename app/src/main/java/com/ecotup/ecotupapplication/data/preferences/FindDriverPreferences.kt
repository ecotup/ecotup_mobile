package com.ecotup.ecotupapplication.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ecotup.ecotupapplication.data.model.FindDriverModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FindDriverPreferences private constructor(private val datastore: DataStore<Preferences>) {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "find_driver")
    suspend fun setDriverOneTime(findDriverModel: FindDriverModel) {
        datastore.edit { pref ->
            pref[ID_DRIVER] = findDriverModel.idDriver
            pref[ID_USER] = findDriverModel.idUser
            pref[LAT] = findDriverModel.latitude
            pref[LONG] = findDriverModel.longitude
            pref[DISTANCE] = findDriverModel.distance

        }
    }

    fun getDriverOneTime(): Flow<FindDriverModel> {
        return datastore.data.map { pref ->
            FindDriverModel(
                pref[ID_DRIVER] ?: "",
                pref[ID_USER] ?: "",
                pref[LAT] ?: "",
                pref[LONG] ?: "",
                pref[DISTANCE] ?: ""
            )
        }
    }

    suspend fun deleteDriverOneTime() {
        datastore.edit { pref ->
            pref[ID_DRIVER] = ""
            pref[ID_USER] = ""
            pref[LAT] = ""
            pref[LONG] = ""
            pref[DISTANCE] = ""
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: FindDriverPreferences? = null
        private val ID_DRIVER = stringPreferencesKey("id_driver")
        private val ID_USER = stringPreferencesKey("id_user")
        private val LAT = stringPreferencesKey("lat")
        private val LONG = stringPreferencesKey("long")
        private val DISTANCE = stringPreferencesKey("distance")

        fun getInstance(datastore: DataStore<Preferences>): FindDriverPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = FindDriverPreferences(datastore)
                INSTANCE = instance
                instance
            }
        }
    }
}