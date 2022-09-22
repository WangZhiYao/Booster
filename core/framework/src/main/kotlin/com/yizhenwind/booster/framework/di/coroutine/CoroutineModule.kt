package com.yizhenwind.booster.framework.di.coroutine

import com.yizhenwind.booster.framework.di.coroutine.qualifier.IODispatcher
import com.yizhenwind.booster.framework.di.coroutine.qualifier.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
@Module
@InstallIn(SingletonComponent::class)
class CoroutineModule {

    @Provides
    @Singleton
    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

}