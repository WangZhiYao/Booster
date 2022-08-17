package com.yizhenwind.booster.data.ext

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
val Context.boosterDataStore: DataStore<Preferences> by preferencesDataStore(name = "booster")
