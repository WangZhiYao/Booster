package com.yizhenwind.booster.data.database.dto

import androidx.room.Embedded
import androidx.room.Relation
import com.yizhenwind.booster.data.database.entity.CategoryEntity
import com.yizhenwind.booster.data.database.entity.SubjectEntity

/**
 *
 * @author WangZhiYao
 * @since 2022/7/20
 */
data class CategoryWithSubjectListDto(
    @Embedded
    val category: CategoryEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "category_id"
    )
    val subjectList: List<SubjectEntity>
)
