package com.yizhenwind.booster.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.yizhenwind.booster.data.ext.boosterDataStore
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

}