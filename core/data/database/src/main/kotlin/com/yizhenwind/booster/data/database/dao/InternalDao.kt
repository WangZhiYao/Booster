package com.yizhenwind.booster.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.yizhenwind.booster.data.database.entity.InternalEntity

/**
 *
 * @author WangZhiYao
 * @since 2022/1/18
 */
@Dao
interface InternalDao : IDao<InternalEntity> {

    @Query("SELECT * FROM internal WHERE sect_id = (SELECT sect_id FROM sect WHERE name = :sectName)")
    suspend fun getInternalBySectName(sectName: String): List<InternalEntity>

}