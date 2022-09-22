package com.yizhenwind.booster.data.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.yizhenwind.booster.data.datastore.DataStoreManager
import com.yizhenwind.booster.data.datastore.boosterDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 *
 * @author WangZhiYao
 * @since 2022/6/5
 */
@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideBoosterDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> =
        appContext.boosterDataStore

    @Provides
    @Singleton
    fun provideDataStoreManager(dataStore: DataStore<Preferences>): DataStoreManager =
        DataStoreManager(dataStore)

}