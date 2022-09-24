package com.yizhenwind.booster.data.database.dto

import androidx.room.Embedded
import androidx.room.Relation
import com.yizhenwind.booster.data.database.entity.ServerEntity
import com.yizhenwind.booster.data.database.entity.ZoneEntity

/**
 *
 * @author WangZhiYao
 * @since 2022/1/18
 */
data class ZoneWithServerListDto(

    /**
     *
     */
    @Embedded
    val zone: ZoneEntity,

    /**
     *
     */
    @Relation(
        parentColumn = "id",
        entityColumn = "zone_id"
    )
    val serverList: List<ServerEntity>
)
