package com.yizhenwind.booster.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.booster.data.database.dto.SectWithInternalListDto
import com.yizhenwind.booster.data.database.entity.SectEntity

/**
 *
 * @author WangZhiYao
 * @since 2022/1/18
 */
@Dao
interface SectDao : IDao<SectEntity> {

    @Query("SELECT * FROM sect")
    suspend fun getSectList(): List<SectEntity>

    @Transaction
    @Query("SELECT * FROM sect")
    suspend fun getSectWithInternalList(): List<SectWithInternalListDto>
}