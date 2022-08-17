package com.yizhenwind.booster.data.database.model

import androidx.room.Embedded
import androidx.room.Relation
import com.yizhenwind.booster.data.database.entity.*

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/4/22
 */
data class CharacterInfo(

    @Embedded
    val character: CharacterEntity,

    @Relation(
        parentColumn = "zone_id",
        entityColumn = "id"
    )
    val zoneEntity: ZoneEntity,

    @Relation(
        parentColumn = "server_id",
        entityColumn = "id"
    )
    val serverEntity: ServerEntity,

    @Relation(
        parentColumn = "sect_id",
        entityColumn = "id"
    )
    val sectEntity: SectEntity,

    @Relation(
        parentColumn = "internal_id",
        entityColumn = "id"
    )
    val internalEntity: InternalEntity
)
