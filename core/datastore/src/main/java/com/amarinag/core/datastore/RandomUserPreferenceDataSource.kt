package com.amarinag.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val pageKey = intPreferencesKey("page")

class RandomUserPreferenceDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    val savedPage: Flow<Int> = dataStore.data.map { preferences -> preferences[pageKey] ?: 1 }

    suspend fun resetPage() {
        dataStore.edit { preferences -> preferences[pageKey] = 1 }
    }

    suspend fun incrementPage() {
        dataStore.edit { settings ->
            val currentPage = settings[pageKey] ?: 0
            settings[pageKey] = currentPage + 1
        }
    }
}