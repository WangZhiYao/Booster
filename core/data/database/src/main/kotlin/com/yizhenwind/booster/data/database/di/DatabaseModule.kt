package com.yizhenwind.booster.data.database.di

import android.content.Context
import androidx.room.Room
import com.yizhenwind.booster.data.database.BoosterDatabase
import com.yizhenwind.booster.data.database.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * 数据库模块依赖
 *
 * @author WangZhiYao
 * @since 2022/3/1
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DATABASE_NAME_BOOSTER = "booster.db"

    @Provides
    @Singleton
    fun provideBoosterDatabase(@ApplicationContext appContext: Context): BoosterDatabase =
        Room.databaseBuilder(
            appContext,
            BoosterDatabase::class.java,
            DATABASE_NAME_BOOSTER
        )
            .build()

    @Provides
    @Singleton
    fun provideCustomerDao(database: BoosterDatabase): CustomerDao =
        database.customerDao()

    @Provides
    @Singleton
    fun provideCharacterDao(database: BoosterDatabase): CharacterDao =
        database.characterDao()

    @Provides
    @Singleton
    fun provideZoneDao(database: BoosterDatabase): ZoneDao =
        database.zoneDao()

    @Provides
    @Singleton
    fun provideServerDao(database: BoosterDatabase): ServerDao =
        database.serverDao()

    @Provides
    @Singleton
    fun provideSectDao(database: BoosterDatabase): SectDao =
        database.sectDao()

    @Provides
    @Singleton
    fun provideInternalDao(database: BoosterDatabase): InternalDao =
        database.internalDao()

    @Provides
    @Singleton
    fun provideOrderDao(database: BoosterDatabase): OrderDao =
        database.orderDao()

    @Provides
    @Singleton
    fun provideCategoryDao(database: BoosterDatabase): CategoryDao =
        database.categoryDao()

    @Provides
    @Singleton
    fun provideSubjectDao(database: BoosterDatabase): SubjectDao =
        database.subjectDao()

}