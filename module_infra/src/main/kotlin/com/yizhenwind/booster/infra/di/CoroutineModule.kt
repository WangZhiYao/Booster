package com.yizhenwind.booster.infra.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import com.yizhenwind.booster.infra.di.qualifier.IODispatcher
import com.yizhenwind.booster.infra.di.qualifier.MainDispatcher
import javax.inject.Singleton

/**
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @Provides
    @Singleton
    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

}