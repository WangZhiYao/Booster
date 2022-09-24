package com.yizhenwind.booster.framework.di.resource

import com.yizhenwind.booster.framework.resource.ResourceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/23
 */
@Module
@InstallIn(SingletonComponent::class)
class ResourceModule {

    @Provides
    @Singleton
    fun provideResourceManager(): ResourceManager = ResourceManager()

}