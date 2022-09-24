package com.yizhenwind.booster.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yizhenwind.booster.data.database.dto.ZoneWithServerListDto
import com.yizhenwind.booster.data.database.entity.ZoneEntity

/**
 *
 * @author WangZhiYao
 * @since 2022/1/18
 */
@Dao
interface ZoneDao : IDao<ZoneEntity> {

    @Query("SELECT * FROM zone")
    suspend fun getZoneList(): List<ZoneEntity>

    @Transaction
    @Query("SELECT * FROM zone")
    suspend fun getZoneWithServerList(): List<ZoneWithServerListDto>
}