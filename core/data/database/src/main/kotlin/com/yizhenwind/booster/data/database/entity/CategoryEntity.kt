package com.yizhenwind.booster.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.yizhenwind.booster.common.constant.Constant

/**
 * 分类表
 *
 * @author WangZhiYao
 * @since 2022/1/24
 */
@Entity(
    tableName = "category",
    indices = [
        Index("title", unique = true)
    ]
)
data class CategoryEntity constructor(

    /**
     * 主键ID
     */
    @PrimaryKey(autoGenerate = true)
    var id: Long = Constant.DEFAULT_ID,

    /**
     * 标题
     */
    val title: String,

    /**
     * 描述
     */
    var description: String? = null,

    /**
     * 创建时间
     */
    @ColumnInfo(name = "create_time")
    val createTime: Long = System.currentTimeMillis()
)