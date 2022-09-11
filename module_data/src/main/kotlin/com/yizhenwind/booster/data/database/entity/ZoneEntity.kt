package com.yizhenwind.booster.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author WangZhiYao
 * @since 2022/1/14
 */
@Entity(tableName = "zone")
data class ZoneEntity(

    /**
     * 主键ID
     */
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    /**
     * 大区名
     */
    val name: String
)
