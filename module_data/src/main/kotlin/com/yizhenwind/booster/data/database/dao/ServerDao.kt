package com.yizhenwind.booster.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.yizhenwind.booster.data.database.entity.ServerEntity

/**
 *
 * @author WangZhiYao
 * @since 2022/1/18
 */
@Dao
interface ServerDao : IDao<ServerEntity> {

    @Query("SELECT * FROM server WHERE zone_id = :zoneId")
    suspend fun getServerListByZoneId(zoneId: Long): List<ServerEntity>

}