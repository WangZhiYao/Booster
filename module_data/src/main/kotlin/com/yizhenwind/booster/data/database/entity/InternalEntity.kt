package com.yizhenwind.booster.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 *
 * @author WangZhiYao
 * @since 2022/1/14
 */
@Entity(
    tableName = "internal",
    foreignKeys = [
        ForeignKey(
            entity = SectEntity::class,
            parentColumns = ["id"],
            childColumns = ["sect_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class InternalEntity(

    /**
     * 主键ID
     */
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    /**
     * 门派ID
     */
    @ColumnInfo(name = "sect_id", index = true)
    val sectId: Long,

    /**
     * 心法名
     */
    val name: String
)
