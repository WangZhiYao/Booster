package com.yizhenwind.booster.data.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * 数据库操作
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
interface IDao<T> {

    /**
     * 插入
     */
    @Insert
    suspend fun insert(t: T): Long?

    /**
     * 批量插入
     */
    @Insert
    suspend fun insert(vararg t: T): List<Long>

    /**
     * 插入
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(t: T): Long

    /**
     * 批量插入
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(t: List<T>): List<Long>

    /**
     * 更新
     */
    @Update
    suspend fun update(t: T)

    /**
     * 删除
     */
    @Delete
    suspend fun delete(t: T): Int
}