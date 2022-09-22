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

    @Query("SELECT * FROM server WHERE zone_id = (SELECT zone_id FROM zone WHERE name = :zoneName)")
    suspend fun getServerByZoneName(zoneName: String): List<ServerEntity>

}