package com.yizhenwind.booster.data.database.model

import androidx.room.Embedded
import androidx.room.Relation
import com.yizhenwind.booster.data.database.entity.InternalEntity
import com.yizhenwind.booster.data.database.entity.SectEntity

/**
 *
 * @author WangZhiYao
 * @since 2022/1/18
 */
data class SectWithInternalList(

    /**
     *
     */
    @Embedded
    val sect: SectEntity,

    /**
     *
     */
    @Relation(
        parentColumn = "id",
        entityColumn = "sect_id"
    )
    val internalList: List<InternalEntity>
)
