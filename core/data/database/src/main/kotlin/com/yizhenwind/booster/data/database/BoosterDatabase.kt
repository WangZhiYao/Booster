package com.yizhenwind.booster.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yizhenwind.booster.data.database.dao.*
import com.yizhenwind.booster.data.database.entity.*

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/16
 */
@Database(
    entities = [
        CustomerEntity::class,
        CharacterEntity::class,
        ZoneEntity::class,
        ServerEntity::class,
        SectEntity::class,
        InternalEntity::class,
        CategoryEntity::class,
        SubjectEntity::class,
        OrderEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BoosterDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao

    abstract fun characterDao(): CharacterDao

    abstract fun zoneDao(): ZoneDao

    abstract fun serverDao(): ServerDao

    abstract fun sectDao(): SectDao

    abstract fun internalDao(): InternalDao

    abstract fun orderDao(): OrderDao

    abstract fun categoryDao(): CategoryDao

    abstract fun subjectDao(): SubjectDao

}