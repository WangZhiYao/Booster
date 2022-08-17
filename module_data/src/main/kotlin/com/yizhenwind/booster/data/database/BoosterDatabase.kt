package com.yizhenwind.booster.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yizhenwind.booster.data.database.converter.BillingCycleConverter
import com.yizhenwind.booster.data.database.converter.ContactTypeConverter
import com.yizhenwind.booster.data.database.converter.OrderStatusConverter
import com.yizhenwind.booster.data.database.converter.PaymentStatusConverter
import com.yizhenwind.booster.data.database.dao.*
import com.yizhenwind.booster.data.database.entity.*

/**
 *
 * @author WangZhiYao
 * @since 2022/3/1
 */
@Database(
    entities = [
        CustomerEntity::class,
        CharacterEntity::class,
        OrderEntity::class,
        SubjectEntity::class,
        CategoryEntity::class,
        ZoneEntity::class,
        ServerEntity::class,
        SectEntity::class,
        InternalEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    BillingCycleConverter::class,
    OrderStatusConverter::class,
    PaymentStatusConverter::class,
    ContactTypeConverter::class
)
abstract class BoosterDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    abstract fun customerDao(): CustomerDao

    abstract fun orderDao(): OrderDao

    abstract fun subjectDao(): SubjectDao

    abstract fun categoryDao(): CategoryDao

    abstract fun zoneDao(): ZoneDao

    abstract fun serverDao(): ServerDao

    abstract fun sectDao(): SectDao

    abstract fun internalDao(): InternalDao
}