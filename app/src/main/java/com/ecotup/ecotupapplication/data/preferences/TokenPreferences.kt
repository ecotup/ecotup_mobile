package com.ecotup.ecotupapplication.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ecotup.ecotupapplication.data.model.PersonModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")

class TokenPreferences private constructor(private val datastore: DataStore<Preferences>) {
    suspend fun setSessionToken(personModel: PersonModel) {
        datastore.edit { pref ->
            pref[ID_KEY] = personModel.id
            pref[TOKEN_KEY] = personModel.token
            pref[ROLE] = personModel.role
        }
    }

    fun getSessionToken(): Flow<PersonModel> {
        return datastore.data.map { pref ->
            PersonModel(
                pref[ID_KEY] ?: "",
                pref[TOKEN_KEY] ?: "",
                pref[ROLE] ?: ""
            )
        }
    }

    suspend fun logout() {
        datastore.edit { pref ->
            pref[ID_KEY] = ""
            pref[TOKEN_KEY] = ""
            pref[ROLE] = ""
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: TokenPreferences? = null
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val ID_KEY = stringPreferencesKey("id")
        private val ROLE = stringPreferencesKey("role")

        fun getInstance(datastore: DataStore<Preferences>): TokenPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = TokenPreferences(datastore)
                INSTANCE = instance
                instance
            }
        }
    }
}