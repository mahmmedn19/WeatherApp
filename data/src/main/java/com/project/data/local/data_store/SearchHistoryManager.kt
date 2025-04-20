/**
 * @author Mohamed Naser.
 */
package com.project.data.local.data_store

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchHistoryManager(private val context: Context) {
    private val Context.dataStore by preferencesDataStore("search_history")
    private val SEARCHED_CITIES_KEY = stringPreferencesKey("searched_cities")

    val lastFiveCities: Flow<List<String>> = context.dataStore.data
        .map { prefs ->
            prefs[SEARCHED_CITIES_KEY]
                ?.split(",")
                ?.filter { it.isNotBlank() }
                ?.take(5)
                ?: emptyList()
        }

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    suspend fun saveCity(city: String) {
        context.dataStore.edit { prefs ->
            val current = prefs[SEARCHED_CITIES_KEY]
                ?.split(",")
                ?.filter { it.isNotBlank() }
                ?.toMutableList() ?: mutableListOf()

            current.remove(city)
            current.add(0, city)

            if (current.size > 5) current.removeLast()
            prefs[SEARCHED_CITIES_KEY] = current.joinToString(",")
        }
    }
}