package com.yizhenwind.booster.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Relation
import com.yizhenwind.booster.data.database.entity.*

/**
 * 客户与角色列表
 *
 * @author WangZhiYao
 * @since 2022/6/22
 */
data class CustomerWithCharacterList(
    @Embedded
    val customer: CustomerEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "customer_id",
        entity = CharacterEntity::class
    )
    val characterList: List<CharacterInfo>
) {

    data class CharacterInfo(
        @Embedded
        val character: Character,

        @Relation(
            parentColumn = "zone_id",
            entityColumn = "id",
        )
        val zoneEntity: ZoneEntity,

        @Relation(
            parentColumn = "server_id",
            entityColumn = "id",
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

    data class Character(
        val id: Long,
        val name: String,
        @ColumnInfo(name = "zone_id")
        val zoneId: Long,
        @ColumnInfo(name = "server_id")
        val serverId: Long,
        @ColumnInfo(name = "sect_id")
        val sectId: Long,
        @ColumnInfo(name = "internal_id")
        val internalId: Long
    )
}
