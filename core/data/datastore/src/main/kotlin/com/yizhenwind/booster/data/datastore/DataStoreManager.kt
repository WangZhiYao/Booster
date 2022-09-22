package com.yizhenwind.booster.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.yizhenwind.booster.logger.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/30
 */
val Context.boosterDataStore: DataStore<Preferences> by preferencesDataStore(name = "datastore_pref_booster")

class DataStoreManager @Inject constructor(private val dataStore: DataStore<Preferences>) {

    fun getInt(name: String, default: Int): Flow<Int> = get(intPreferencesKey(name), default)

    suspend fun setInt(name: String, value: Int) {
        set(intPreferencesKey(name), value)
    }

    private fun <T> get(key: Preferences.Key<T>, default: T): Flow<T> =
        dataStore.data
            .catch {
                Logger.e(it)
                emit(emptyPreferences())
            }
            .map { preferences ->
                preferences[key] ?: default
            }

    private suspend fun <T> set(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences -> preferences[key] = value }
    }

}